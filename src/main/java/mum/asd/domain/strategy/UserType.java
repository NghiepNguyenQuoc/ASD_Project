package mum.asd.domain.strategy;

import mum.asd.domain.HotelUser;

public class UserType {
	private UserStrategy userStrategy;
	
	public void setStrategy(UserStrategy userStrategy) {
		this.userStrategy = userStrategy;
	}
	
	public HotelUser createUser(HotelUser user) {
		return userStrategy.setUserType(user);
	}
}
