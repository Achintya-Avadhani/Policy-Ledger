package com.hdfclife.ledger.repo;

import com.hdfclife.ledger.domain.Policy;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    boolean existsByPolicyNo(String policyNo);

    Optional<Policy> findByPolicyNo(String policyNo);

    @Query("""
            select distinct p from Policy p
            join fetch p.customer
            left join fetch p.riders
            where p.policyNo = :policyNo
            """)
    Optional<Policy> findDetailedByPolicyNo(@Param("policyNo") String policyNo);

    @Query("""
            select distinct p from Policy p
            join fetch p.customer
            left join fetch p.riders
            """)
    List<Policy> findAllDetailed();

    List<Policy> findByCustomerId(Long customerId);
}
