package com.myhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhotel.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, InternalError> {
}