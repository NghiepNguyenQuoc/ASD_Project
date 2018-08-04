package mum.asd.domain.userprofiletemplate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import mum.asd.domain.Booking;
import mum.asd.domain.Card;
import mum.asd.domain.HotelUser;

/*
 * Template Method Design Pattern
 * Tan Tho Nguyen
 * Concrete Class
 */

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
