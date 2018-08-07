package com.myhotel.patterns.Mediator;

import com.myhotel.domain.HotelUser;

public abstract class LoginCustomer {
	protected ValidationMediator validationMediator;
    protected HotelUser hotelUser;
    
    public LoginCustomer(ValidationMediator mediator, HotelUser user) {
    	this.validationMediator = mediator;
    	this.hotelUser = user;
    }
    
    public abstract boolean isValidLoginCustomer();
}
