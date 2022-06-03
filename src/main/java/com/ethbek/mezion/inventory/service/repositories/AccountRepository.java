package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Account;
import com.ethbek.mezion.inventory.service.models.entites.Customer;
import com.ethbek.mezion.inventory.service.models.entites.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Page<Account> findByCustomer(Customer customer, Pageable pageable);
    Page<Account> findByLocation(Location location, Pageable pageable);
    Optional<Account> findByAccountId(String accountId);
}
