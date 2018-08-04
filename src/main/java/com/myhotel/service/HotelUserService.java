package com.myhotel.service;

import com.myhotel.domain.HotelUser;
import com.myhotel.domain.User;
import com.myhotel.generic.GenericService;

// Real subject of Proxy
public interface HotelUserService extends GenericService<HotelUser> {

	boolean authenticate(String email, String password);
	
	HotelUser findByEmail(String email);
	
}
