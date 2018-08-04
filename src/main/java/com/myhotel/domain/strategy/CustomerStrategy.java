package com.myhotel.domain.strategy;

import com.myhotel.domain.HotelUser;
import com.myhotel.domain.UserType;

public class CustomerStrategy implements UserStrategy {

	@Override
	public HotelUser setUserType(HotelUser user) {
		// TODO Auto-generated method stub
		user.setUserType(UserType.Customer);
		return user;
	}
	
}
