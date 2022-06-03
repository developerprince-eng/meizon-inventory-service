package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    Optional<Payment> findByPaymentId(String paymentId);

    Page<Payment> findByPaymentDate(Date date, Pageable pageable);

    Page<Payment> findAll(Pageable pageable);
}
