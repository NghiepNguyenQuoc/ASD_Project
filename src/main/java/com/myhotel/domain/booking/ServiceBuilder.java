package com.myhotel.domain.booking;

import java.util.List;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Room;

public interface ServiceBuilder {
	public void createBooking(String startDate, String endDate);
	public void setRoomsToBooking(List<Room> lstRoom);
	public void saveBooking();
	
	public void checkInBooking(List<Booking> lstBookings);
	public void checkOutBooking(List<Booking> lstBookings);
}
