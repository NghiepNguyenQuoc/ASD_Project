package com.myhotel.domain.userprofiletemplate;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Card;
import com.myhotel.domain.HotelUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class ViewUserInformation extends UserProfile {
	private ObservableList<Booking> bookingListObservable = FXCollections.observableArrayList();
	private ObservableList<Card> cardListObservable = FXCollections.observableArrayList();

	@Override
	protected void readUserBooking(HotelUser user, TableView<Booking> bookingTableView) {
		// TODO Auto-generated method stub
		bookingListObservable.clear();
		
		bookingListObservable.addAll(user.getBookingList());
		bookingTableView.setItems(bookingListObservable);
	}

	@Override
	protected void readUserCard(HotelUser user, TableView<Card> cardTableView) {
		// TODO Auto-generated method stub
		cardListObservable.clear();
		
		cardListObservable.addAll(user.getPayment().getCards());
		cardTableView.setItems(cardListObservable);
		
	}


}
