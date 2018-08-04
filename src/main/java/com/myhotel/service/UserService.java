package com.myhotel.service;

import org.springframework.data.repository.query.Param;

import com.myhotel.domain.HotelUser;
import com.myhotel.domain.User;
import com.myhotel.generic.GenericService;

import java.util.List;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);

//	List<HotelUser> findHotelUserByFirstName(@Param("firstName") String firstName);
	
}
