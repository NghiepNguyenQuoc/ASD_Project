package com.myhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhotel.domain.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
