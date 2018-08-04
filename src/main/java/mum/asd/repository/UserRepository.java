package mum.asd.repository;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@Query("SELECT distinct h from HotelUser h WHERE h.firstName = :firstname")
//	List<HotelUser> findHotelUserByFirstName(@Param("firstName") String firstName);

	User findByEmail(String email);
}
