package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Pump;
import com.ethbek.mezion.inventory.service.models.entites.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PumpRepository extends JpaRepository<Pump, String> {
    List<Pump> findByTank(Tank tank);
}
