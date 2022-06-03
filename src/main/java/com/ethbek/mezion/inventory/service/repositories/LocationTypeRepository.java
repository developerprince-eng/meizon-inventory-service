package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, String> {
    List<LocationType> findAll();
    Optional<LocationType> findByType(String type);
}
