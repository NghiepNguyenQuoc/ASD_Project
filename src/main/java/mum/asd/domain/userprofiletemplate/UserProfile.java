package mum.asd.domain.userprofiletemplate;

import javafx.scene.control.TableView;
import mum.asd.domain.Booking;
import mum.asd.domain.Card;
import mum.asd.domain.HotelUser;

/*
 * Template Method Design Pattern
 * Tan Tho Nguyen
 * Abstract class
 */

public abstract class UserProfile {
	public void showProfileUser(HotelUser user, TableView<Booking> bookingTableView, TableView<Card> cardTableView) {
		readUserCard(user, cardTableView);
		readUserBooking(user, bookingTableView);
	}
	
	protected abstract void readUserBooking(HotelUser user, TableView<Booking> bookingTableView);
	
	protected abstract void readUserCard(HotelUser user, TableView<Card> cardTableView);

}
