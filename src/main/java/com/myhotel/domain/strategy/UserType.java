package com.myhotel.domain.strategy;

import com.myhotel.domain.HotelUser;

public class UserType {
	private UserStrategy userStrategy;
	
	public void setStrategy(UserStrategy userStrategy) {
		this.userStrategy = userStrategy;
	}
	
	public HotelUser createUser(HotelUser user) {
		return userStrategy.setUserType(user);
	}
}
