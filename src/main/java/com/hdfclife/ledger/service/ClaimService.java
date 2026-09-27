package com.hdfclife.ledger.service;

import com.hdfclife.ledger.domain.Claim;
import com.hdfclife.ledger.domain.Policy;
import com.hdfclife.ledger.dto.ClaimResponse;
import com.hdfclife.ledger.dto.CreateClaimRequest;
import com.hdfclife.ledger.exception.ClaimNotFoundException;
import com.hdfclife.ledger.exception.InvalidRequestException;
import com.hdfclife.ledger.exception.PolicyNotFoundException;
import com.hdfclife.ledger.repo.ClaimRepository;
import com.hdfclife.ledger.repo.PolicyRepository;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClaimService {

    private static final String ACTIVE_STATUS = "Active";

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimService(ClaimRepository claimRepository, PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    public ClaimResponse createClaim(CreateClaimRequest request) {
        String policyNo = request.getPolicyNo().trim();
        Policy policy = policyRepository.findByPolicyNo(policyNo)
                .orElseThrow(() -> new PolicyNotFoundException(policyNo));

        if (!ACTIVE_STATUS.equalsIgnoreCase(policy.getStatus())) {
            throw new InvalidRequestException(
                    "Claims can only be filed against Active policies. Current status: " + policy.getStatus());
        }

        String claimNo = nextClaimNo();
        Claim claim = new Claim(
                claimNo,
                policy,
                request.getClaimAmount(),
                request.getUrgency().trim().toUpperCase(Locale.ROOT),
                request.getHospitalName().trim());

        Claim saved = claimRepository.save(claim);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ClaimResponse getByClaimNo(String claimNo) {
        Claim claim = claimRepository.findDetailedByClaimNo(claimNo)
                .orElseThrow(() -> new ClaimNotFoundException(claimNo));
        return toResponse(claim);
    }

    @Transactional(readOnly = true)
    public List<ClaimResponse> listClaims() {
        return claimRepository.findAllDetailed().stream()
                .map(this::toResponse)
                .toList();
    }

    private String nextClaimNo() {
        long next = claimRepository.count() + 1;
        String candidate = String.format("CLM-%02d", next);
        while (claimRepository.existsByClaimNo(candidate)) {
            next++;
            candidate = String.format("CLM-%02d", next);
        }
        return candidate;
    }

    private ClaimResponse toResponse(Claim claim) {
        return new ClaimResponse(
                claim.getId(),
                claim.getClaimNo(),
                claim.getPolicy().getPolicyNo(),
                claim.getClaimAmount(),
                claim.getUrgency(),
                claim.getHospitalName());
    }
}
