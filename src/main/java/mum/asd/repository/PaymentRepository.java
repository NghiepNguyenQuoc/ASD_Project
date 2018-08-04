package mum.asd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.asd.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
