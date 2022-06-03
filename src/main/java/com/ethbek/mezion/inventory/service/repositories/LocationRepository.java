package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.models.entites.LocationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, String > {

    Page<Location> findAll(Pageable pageable);

    Optional<Location> findByLocationId(String locationId);

    Optional<Location> findByName(String locationName);

    Page<Location> findByLocationType(LocationType locationType, Pageable pageable);

}
