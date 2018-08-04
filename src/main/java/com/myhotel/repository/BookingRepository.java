package com.myhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhotel.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
