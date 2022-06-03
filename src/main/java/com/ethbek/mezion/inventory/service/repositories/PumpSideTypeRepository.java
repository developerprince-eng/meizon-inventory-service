package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.PumpSideType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PumpSideTypeRepository extends JpaRepository<PumpSideType, Integer> {
    Optional<PumpSideType> findBySide(String side);
}
