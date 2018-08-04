package mum.asd.repository;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
	HotelUser findByEmail(String email);
}
