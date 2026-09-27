package com.hdfclife.ledger.repo;

import com.hdfclife.ledger.domain.Claim;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Optional<Claim> findByClaimNo(String claimNo);

    boolean existsByClaimNo(String claimNo);

    @Query("""
            select c from Claim c
            join fetch c.policy p
            where c.claimNo = :claimNo
            """)
    Optional<Claim> findDetailedByClaimNo(@Param("claimNo") String claimNo);

    @Query("""
            select c from Claim c
            join fetch c.policy p
            """)
    List<Claim> findAllDetailed();

    List<Claim> findByPolicyId(Long policyId);

    long count();
}
