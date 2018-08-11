package com.myhotel.patterns.Mediator;

import java.util.ArrayList;
import java.util.List;

public class PromotionMediatorImpl implements PromotionMediator {

	public List<HotelCustomer> getHotelCustomers() {
		return hotelCustomers;
	}

	public void setHotelCustomers(List<HotelCustomer> hotelCustomers) {
		this.hotelCustomers = hotelCustomers;
	}

	List<HotelCustomer> hotelCustomers;

	public PromotionMediatorImpl() {
		this.hotelCustomers = new ArrayList<HotelCustomer>();
	}

	@Override
	public void broadCastPromotion(String promotionName, HotelCustomer hotelCustomer) {
		for (HotelCustomer hotelCustomer1 : hotelCustomers) {
			if (hotelCustomer1 != null && hotelCustomer.hotelUser.getId() != hotelCustomer1.hotelUser.getId()) {
				hotelCustomer1.receivePromotion(promotionName);
			}
		}
	}

	@Override
	public void addHotelCustomer(HotelCustomer hotelCustomer) {
		hotelCustomers.add(hotelCustomer);
	}
}
