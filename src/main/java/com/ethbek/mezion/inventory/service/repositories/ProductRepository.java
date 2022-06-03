package com.ethbek.mezion.inventory.service.repositories;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.ethbek.mezion.inventory.service.models.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductSku(String productSku);

    Optional<Product> findByName(String name);
}