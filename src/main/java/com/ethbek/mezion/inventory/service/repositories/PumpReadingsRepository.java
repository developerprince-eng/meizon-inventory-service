package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.PumpReadings;
import com.ethbek.mezion.inventory.service.models.entites.PumpReadingsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PumpReadingsRepository extends JpaRepository<PumpReadings, PumpReadingsKey> {
    Optional<PumpReadings> findByUserAndCreatedDate(String userEmail, Date date);
    Optional<PumpReadings> findByPumpReadingId(String id);
    List<PumpReadings> findByPumpSide(String pumpSideId);
}
