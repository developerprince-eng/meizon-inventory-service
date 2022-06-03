package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.models.entites.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    Page<Stock> findAllByLocation(Location location, Pageable pageable);
}
