package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
    Optional<ProductType> findByType(String type);
}
