package com.myhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhotel.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
