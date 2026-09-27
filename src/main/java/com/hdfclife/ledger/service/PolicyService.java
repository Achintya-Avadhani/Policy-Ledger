package com.hdfclife.ledger.service;

import com.hdfclife.ledger.domain.Customer;
import com.hdfclife.ledger.domain.Policy;
import com.hdfclife.ledger.domain.Rider;
import com.hdfclife.ledger.dto.CreatePolicyRequest;
import com.hdfclife.ledger.dto.PolicyResponse;
import com.hdfclife.ledger.exception.DuplicatePolicyException;
import com.hdfclife.ledger.exception.InvalidRequestException;
import com.hdfclife.ledger.exception.PolicyNotFoundException;
import com.hdfclife.ledger.repo.CustomerRepository;
import com.hdfclife.ledger.repo.PolicyRepository;
import com.hdfclife.ledger.repo.RiderRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PolicyService {

    private static final String DEFAULT_STATUS = "Active";

    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;
    private final RiderRepository riderRepository;

    public PolicyService(
            PolicyRepository policyRepository,
            CustomerRepository customerRepository,
            RiderRepository riderRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
        this.riderRepository = riderRepository;
    }

    public PolicyResponse createPolicy(CreatePolicyRequest request) {
        String policyNo = request.getPolicyNo().trim();
        if (policyRepository.existsByPolicyNo(policyNo)) {
            throw new DuplicatePolicyException(policyNo);
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new InvalidRequestException(
                        "Customer not found with id: " + request.getCustomerId()));

        String productType = request.getProductType().trim().toUpperCase(Locale.ROOT);
        Policy policy = new Policy(
                policyNo,
                customer,
                productType,
                request.getBasePremium(),
                DEFAULT_STATUS);

        Set<Rider> riders = resolveRiders(request.getRiderCodes());
        policy.setRiders(riders);

        Policy saved = policyRepository.save(policy);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public PolicyResponse getByPolicyNo(String policyNo) {
        Policy policy = policyRepository.findDetailedByPolicyNo(policyNo)
                .orElseThrow(() -> new PolicyNotFoundException(policyNo));
        return toResponse(policy);
    }

    @Transactional(readOnly = true)
    public List<PolicyResponse> listPolicies() {
        return policyRepository.findAllDetailed().stream()
                .map(this::toResponse)
                .toList();
    }

    private Set<Rider> resolveRiders(List<String> riderCodes) {
        if (riderCodes == null || riderCodes.isEmpty()) {
            return new HashSet<>();
        }

        List<String> normalized = riderCodes.stream()
                .filter(code -> code != null && !code.isBlank())
                .map(code -> code.trim().toUpperCase(Locale.ROOT))
                .distinct()
                .toList();

        if (normalized.isEmpty()) {
            return new HashSet<>();
        }

        List<Rider> found = riderRepository.findByRiderCodeIn(normalized);
        if (found.size() != normalized.size()) {
            Set<String> foundCodes = found.stream()
                    .map(Rider::getRiderCode)
                    .collect(Collectors.toSet());
            List<String> missing = normalized.stream()
                    .filter(code -> !foundCodes.contains(code))
                    .toList();
            throw new InvalidRequestException("Unknown rider code(s): " + String.join(", ", missing));
        }
        return new HashSet<>(found);
    }

    private PolicyResponse toResponse(Policy policy) {
        List<String> riderCodes = policy.getRiders().stream()
                .map(Rider::getRiderCode)
                .sorted()
                .toList();

        return new PolicyResponse(
                policy.getId(),
                policy.getPolicyNo(),
                policy.getCustomer().getId(),
                policy.getCustomer().getFullName(),
                policy.getProductType(),
                policy.getBasePremium(),
                policy.getStatus(),
                riderCodes);
    }
}
