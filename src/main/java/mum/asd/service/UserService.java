package mum.asd.service;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.generic.GenericService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);

//	List<HotelUser> findHotelUserByFirstName(@Param("firstName") String firstName);
	
}
