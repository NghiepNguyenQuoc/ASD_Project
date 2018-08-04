package mum.asd.domain.strategy;

import mum.asd.domain.HotelUser;
import mum.asd.domain.UserType;

public class CustomerStrategy implements UserStrategy {

	@Override
	public HotelUser setUserType(HotelUser user) {
		// TODO Auto-generated method stub
		user.setUserType(UserType.Customer);
		return user;
	}
	
}
