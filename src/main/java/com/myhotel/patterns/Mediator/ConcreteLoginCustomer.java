package com.myhotel.patterns.Mediator;

import com.myhotel.domain.HotelUser;

public class ConcreteLoginCustomer extends LoginCustomer {

	public ConcreteLoginCustomer(ValidationMediator mediator, HotelUser user) {
		super(mediator, user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidLoginCustomer() {
		// TODO Auto-generated method stub
		return this.validationMediator.isValidHotelCustomer(this);
	}

}
