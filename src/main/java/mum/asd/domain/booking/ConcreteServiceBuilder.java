package mum.asd.domain.booking;

import java.util.Date;
import java.util.List;

import mum.asd.domain.Booking;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Room;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.BookingService;
import mum.asd.service.HotelUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * @author vynguyen
 *
 */
//@Component
//@Configurable
	@Transactional
public class ConcreteServiceBuilder implements ServiceBuilder {
//	@Autowired
	private HotelUser user;
	private Booking booking;

	@Autowired
	BookingService bookingService;

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
}
