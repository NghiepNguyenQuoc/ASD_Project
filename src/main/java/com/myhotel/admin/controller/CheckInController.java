package com.myhotel.admin.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Booking;
import com.myhotel.domain.Card;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.Promotion;
import com.myhotel.domain.Room;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.domain.booking.ConcreteServiceBuilder;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.domain.bookingprices.ServiceElementDoVisitor;
import com.myhotel.repository.HotelUserRepository;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.BookingServiceImpl;
import com.myhotel.service.impl.RoomServiceImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class CheckInController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;

	@Autowired
	BookingServiceImpl bookingService;
	private ObservableList<Booking> masterListObservable = FXCollections.observableArrayList();
	private ObservableList<Booking> bookingListObservable = FXCollections.observableArrayList();
	

	@FXML
	private Button pay;
	
	//updated
	@FXML
	private TableView<Booking> bookingTableView;
//	@FXML
//	private TableView<BookingRoom> bookingTable;
	
	
	@FXML
	private TableColumn<Room, Long> colBookingNumber;

	@FXML
	private TableColumn<Room, LocalDate> colStartDate;

	@FXML
	private TableColumn<Room, LocalDate> colEndDate;

	@FXML
	private TableColumn<Room, String> colCustomer;
	
	@FXML
    private TableColumn<Room, String> colRoomsNumber;

	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		goToViewRoomLayout();
	}
	
	@FXML
    private void pay(ActionEvent event) {
//		if (this.cardNumber.getValue() != null) {
//			this.serviceDirector.getServiceBuilder().saveBooking();
//			HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("booking.completed"), 
//					AlertType.INFORMATION);
//			goToViewRoomLayout();
//		} else {
//			HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("booking.warning"), 
//							AlertType.WARNING);
//		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// Update message to say hello to user
		if (currentUser != null) {
//			userMess.setText("Hello " + currentUser.getFirstName() + " !");
		}
		
		setColumnProperties();
		loadBookingTable();
	}

	
	public void loadBookingTable(){
		List<Booking> bookings = null;
		if (bookingService==null)
			bookingService = ApplicationContextHolder.getContext().getBean(BookingServiceImpl.class);
		
		bookings = bookingService.findAll();
		bookings.removeIf(b -> (b.getCheckInStatus()==null? false : b.getCheckInStatus()) == true);
		
		bookingListObservable.clear();
		bookingListObservable.addAll(bookings);
		bookingTableView.setItems(bookingListObservable);
		
	}

    private void setColumnProperties() {
    	colBookingNumber.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
    	colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    	colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

    	colCustomer.setCellValueFactory(cellData -> {
            String customerName = "";

            return new ReadOnlyStringWrapper(customerName);
        });
    	colRoomsNumber.setCellValueFactory(cellData -> {
            String roomsNumber = "";
            

            return new ReadOnlyStringWrapper(roomsNumber);
        });
	}

	
	// Use for passing data from view book controller to booking controller
	public void setServiceDirector(ServiceDirector serviceDirector) {
		
		// Using builder pattern to do booking action
		this.serviceDirector = serviceDirector;
		ConcreteServiceBuilder concreteServiceBuilder =
						(ConcreteServiceBuilder)this.serviceDirector.getServiceBuilder();
		
		// Show user information to GUI
//		this.name.setText(concreteServiceBuilder.getUser().getFirstName() + 
//				" " + concreteServiceBuilder.getUser().getLastName());
//		this.address.setText(concreteServiceBuilder.getUser().getAddress().toString());
		
		// Show list cards of user if have
		List<String> numCard = new ArrayList<>();
		for (Card c : concreteServiceBuilder.getUser().getPayment().getCards()) {
			String cardNumber = c.getCardNumber();
			numCard.add("xxxxxx" + cardNumber.substring(0, cardNumber.length() - 5));
		}
		
//		this.cardNumber.getItems().addAll(numCard);
//		this.startDate.setText(concreteServiceBuilder.getBooking().getStartDate_S());
//		this.endDate.setText(concreteServiceBuilder.getBooking().getEndDate_S());
		
		List<Room> lstRoom = concreteServiceBuilder.getBooking().getRooms();
//		concreteServiceBuilder.getBooking().
		Double discountPercent = 0.0;
		// Verify discount
		if (lstRoom.size() > 2 && lstRoom.size() < 5) {
			discountPercent = 0.1;
		} else if (lstRoom.size() > 5) {
			discountPercent = 0.3;
		}
//		this.discount.setText(String.valueOf(discountPercent));
		
		// Count total price using visitor pattern
		ServiceElementDoVisitor serviceElementVisitor = new ServiceElementDoVisitor();
		for (Room r : lstRoom) {
			r.accept(serviceElementVisitor);
		}
		double finalPrice = serviceElementVisitor.getPrice();
		if (discountPercent > 0) {
			finalPrice = finalPrice - (finalPrice * discountPercent);
		}
//		finalPrice = finalPrice - (finalPrice * (float)loadPromotion()/100);

//		this.totalPrice.setText(String.valueOf(finalPrice));
		
		
		// Load list room to table view
		ObservableList<Room> data = FXCollections.observableArrayList(lstRoom);
		colBookingNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		colStartDate.setCellValueFactory(new PropertyValueFactory<>("price"));
		colEndDate.setCellValueFactory(new PropertyValueFactory<>("bedType"));
		colCustomer.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
		colRoomsNumber.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
		
		data.clear();
		data.addAll(lstRoom);
//		roomsTable.setItems(data);
		
	}
	
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
