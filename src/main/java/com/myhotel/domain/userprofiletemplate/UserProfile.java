package com.myhotel.domain.userprofiletemplate;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Card;
import com.myhotel.domain.HotelUser;

import javafx.scene.control.TableView;


public abstract class UserProfile {
	public void showProfileUser(HotelUser user, TableView<Booking> bookingTableView, TableView<Card> cardTableView) {
		readUserCard(user, cardTableView);
		readUserBooking(user, bookingTableView);
	}
	
	protected abstract void readUserBooking(HotelUser user, TableView<Booking> bookingTableView);
	
	protected abstract void readUserCard(HotelUser user, TableView<Card> cardTableView);

}
