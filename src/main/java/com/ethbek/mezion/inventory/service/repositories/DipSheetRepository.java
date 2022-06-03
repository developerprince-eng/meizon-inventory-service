package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.DipSheet;
import com.ethbek.mezion.inventory.service.models.entites.DipSheetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipSheetRepository extends JpaRepository<DipSheet, DipSheetKey> {
    Optional<DipSheet> findByMetricMeasurement(Integer value);
}
