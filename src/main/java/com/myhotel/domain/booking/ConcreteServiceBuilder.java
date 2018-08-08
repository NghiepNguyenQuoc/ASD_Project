package com.myhotel.domain.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myhotel.domain.Booking;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.Room;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.BookingService;
import com.myhotel.service.HotelUserService;
import com.myhotel.service.RoomService;

import javax.transaction.Transactional;


//@Component
//@Configurable
	@Transactional
public class ConcreteServiceBuilder implements ServiceBuilder {
//	@Autowired
	private HotelUser user;
	private Booking booking;

	@Autowired
	BookingService bookingService;
	@Autowired
	RoomService roomService;

	public ConcreteServiceBuilder(HotelUser user) {
		super();
		this.user = user;
	}

	@Override
	public void createBooking(String startDate, String endDate) {
		// TODO Auto-generated method stub
		String bookingNum = String.valueOf(new Date().getTime()/100);
		this.booking = new Booking(startDate, endDate);
		this.booking.setBookingNumber(bookingNum);
		this.booking.setPayment(this.user.getPayment());
	}

	@Override
	public void setRoomsToBooking(List<Room> lstRoom) {
		// TODO Auto-generated method stub
		this.booking.setRooms(lstRoom);
	}

	@Override
	public void saveBooking() {
		// Call api to save to booking table
		bookingService = ApplicationContextHolder.getContext().getBean(BookingService.class);
		bookingService.save(this.booking);
		
		// Save user to have relationship
		HotelUserService userService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
		this.user.addBookingToBookingList(this.booking);
		userService.save(this.user);
		user.getPayment().getCards().size();
	}

	public HotelUser getUser() {
		HotelUserService userService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
		user = userService.findByEmail(user.getEmail());
//		user.getPayment().getCards().size();
		return user;
	}

	public Booking getBooking() {
		return booking;
	}
	
	public List<Booking> getBookings(String startDate, String endDate){
		
		return null;
	}
	
	public void checkInBooking(List<Booking> lstBookings) {
		bookingService = ApplicationContextHolder.getContext().getBean(BookingService.class);
		for(Booking b : lstBookings) {
			b.setCheckInStatus(true);
			bookingService.save(b);
		}
		
	}
	public void checkOutBooking(List<Booking> lstBookings) {
		bookingService = ApplicationContextHolder.getContext().getBean(BookingService.class);
		roomService = ApplicationContextHolder.getContext().getBean(RoomService.class);
		for(Booking b : lstBookings) {
			b.setCheckOutStatus(true);
			bookingService.save(b);
			
			List<Room> bookedRooms = b.getRooms();
			for(Room r : bookedRooms) {
				System.out.println("Returning room: " + r.getRoomNumber());
				r.setRoomVailable(true);
				roomService.update(r);
			}
		}
		
	}
}
