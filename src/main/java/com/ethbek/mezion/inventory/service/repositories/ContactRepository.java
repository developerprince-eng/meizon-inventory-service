package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Contact;
import com.ethbek.mezion.inventory.service.models.entites.ContactType;
import com.ethbek.mezion.inventory.service.models.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    Optional<Contact> findByDetail(String detail);
    List<Contact> findByCustomer(Customer customer);
}
