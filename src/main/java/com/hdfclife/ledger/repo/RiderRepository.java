package com.hdfclife.ledger.repo;

import com.hdfclife.ledger.domain.Rider;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByRiderCode(String riderCode);

    List<Rider> findByRiderCodeIn(Collection<String> riderCodes);
}
