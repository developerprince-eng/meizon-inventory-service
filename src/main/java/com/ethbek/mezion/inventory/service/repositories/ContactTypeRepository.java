package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, String> {
    Optional<ContactType> findByDescriptorType(String type);
}
