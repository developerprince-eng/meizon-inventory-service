package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {
  Optional<Region> findByProvince(String province);
  List<Region> findAll();
}
