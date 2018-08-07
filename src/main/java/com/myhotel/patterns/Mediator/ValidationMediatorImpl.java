package com.myhotel.patterns.Mediator;

import com.myhotel.domain.HotelUser;
import com.myhotel.domain.validator.HotelValidator;

public class ValidationMediatorImpl implements ValidationMediator {
	
	@Override
	public boolean isValidHotelCustomer(LoginCustomer loginCustomer) {
		// TODO Auto-generated method stub
		HotelUser user = loginCustomer.hotelUser;
		
		if(user.getEmail().equals("") || user.getPassword().equals(""))
			return false;
		
		
		if(HotelValidator.validate("Email", user.getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
			return true;
		}
		
		return false;
	}

}
