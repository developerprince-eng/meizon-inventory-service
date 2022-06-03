package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.City;
import com.ethbek.mezion.inventory.service.models.entites.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String > {
    Optional<Customer> findByCustomerId(String customerId);
    Optional<Customer> findByFirstNameAndLastNameAndCompanyName(String firstName, String lastName, String companyName);
    Page<Customer> findByLastName(String lastName, Pageable pageable);
    Page<Customer> findByFirstName(String firstName, Pageable pageable);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByCity(City city, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "DELETE from public.customer  where customer_id=:customerId", nativeQuery = true)
    void deleteByCustomerId(@Param("customerId") String customerId);

    Page<Customer> findByCompanyName(String companyName, Pageable pageable);

}
