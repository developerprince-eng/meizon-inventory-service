package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.PumpSide;
import com.ethbek.mezion.inventory.service.models.entites.PumpSideKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PumpSideRepository extends JpaRepository<PumpSide, PumpSideKey> {
    Optional<PumpSide> findByPumpSideId(String pumpSideId);
}
