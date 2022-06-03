package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.User;
import com.ethbek.mezion.inventory.service.models.entites.UserIdKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, UserIdKey> {

    Optional<User> findByEmailAndLocationId(String email, String locationId);

    Set<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    void deleteAllByEmail(String email);

}
