package com.myhotel.domain.booking;

import java.util.Date;
import java.util.List;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Room;

public class ServiceDirector {
	private ServiceBuilder serviceBuilder;

	public ServiceDirector(ServiceBuilder serviceBuilder) {
		super();
		this.serviceBuilder = serviceBuilder;
	}

	public ServiceBuilder getServiceBuilder() {
		return serviceBuilder;
	}

	public void setServiceBuilder(ServiceBuilder serviceBuilder) {
		this.serviceBuilder = serviceBuilder;
	}
	
	public void createBooking(String startDate, String endDate) {
		this.serviceBuilder.createBooking(startDate, endDate);
	}
	
	public void setRoomsToBooking(List<Room> lstRoom) {
		this.serviceBuilder.setRoomsToBooking(lstRoom);
	}
	
	public void saveBooking() {
		this.serviceBuilder.saveBooking();
	}
	
	public void checkInBooking(List<Booking> lstBookings) {
		this.serviceBuilder.checkInBooking(lstBookings);
	}
	public void checkOutBooking(List<Booking> lstBookings) {
		this.serviceBuilder.checkOutBooking(lstBookings);
	}
}
