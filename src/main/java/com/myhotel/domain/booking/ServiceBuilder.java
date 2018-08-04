package com.myhotel.domain.booking;

import java.util.Date;
import java.util.List;

import com.myhotel.domain.Room;

/**
 * @author vynguyen
 *
 */
public interface ServiceBuilder {
	public void createBooking(String startDate, String endDate);
	public void setRoomsToBooking(List<Room> lstRoom);
	public void saveBooking();
}
