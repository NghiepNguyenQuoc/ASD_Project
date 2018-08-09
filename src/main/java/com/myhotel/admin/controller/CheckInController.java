package com.myhotel.admin.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Booking;
import com.myhotel.domain.Room;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.domain.booking.ConcreteServiceBuilder;
import com.myhotel.domain.booking.ServiceBuilder;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.BookingServiceImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class CheckInController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;
	private int currentIndex = 0;
	public List<Booking> selectedBookings;

	@Autowired
	BookingServiceImpl bookingService;
	private ObservableList<Booking> bookingListObservable = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Booking> bookingTableView;
	
	
	@FXML
	private TableColumn<Booking, Long> colBookingNumber;

	@FXML
	private TableColumn<Booking, LocalDate> colStartDate;

	@FXML
	private TableColumn<Booking, LocalDate> colEndDate;

	@FXML
    private TableColumn<Booking, String> colCheckIn;
	@FXML
    private TableColumn<Booking, String> colCheckOut;
	
	@FXML
    private TableColumn<Booking, String> colRoomsNumber;

	@FXML
	private Button checkIn;
	@FXML
	private Button checkOut;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// Update message to say hello to user
		if (currentUser != null) {
//			userMess.setText("Hello " + currentUser.getFirstName() + " !");
		}
		
		setColumnProperties();
		addSelectionListener();
		loadBookingTable();
	}

	public void addSelectionListener(){
		bookingTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedBookings = bookingTableView.getSelectionModel().getSelectedItems();});
	}
	
	public void loadBookingTable(){
		List<Booking> bookings = null;
		selectedBookings = null;
		if (bookingService==null)
			bookingService = ApplicationContextHolder.getContext().getBean(BookingServiceImpl.class);
		
		bookingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		bookings = bookingService.findAll();
		bookings.removeIf(b -> (b.getCheckOutStatus()==null? false : b.getCheckOutStatus()) == true);
		
		bookingListObservable.clear();
		bookingListObservable.addAll(bookings);
		bookingTableView.setItems(bookingListObservable);
		
	}

    private void setColumnProperties() {
    	colBookingNumber.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
    	colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate_S"));
    	colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate_S"));

    	colCheckIn.setCellValueFactory(cellData -> {
            boolean checkIn = cellData.getValue().getCheckInStatus()==null? 
            					false: cellData.getValue().getCheckInStatus();
            String checkInAsString;
            if(checkIn == true)
            {
            	checkInAsString = "Checked in";
            }
            else
            {
            	checkInAsString = "No";
            }

            return new ReadOnlyStringWrapper(checkInAsString);
        });
    	colCheckOut.setCellValueFactory(cellData -> {
            boolean checkOut = cellData.getValue().getCheckOutStatus()==null? 
								false: cellData.getValue().getCheckOutStatus();
            String checkOutAsString;
            if(checkOut == true)
            {
            	checkOutAsString = "Checked out";
            }
            else
            {
            	checkOutAsString = "No";
            }

            return new ReadOnlyStringWrapper(checkOutAsString);
        });
    	
    	colRoomsNumber.setCellValueFactory(cellData -> {
    		String roomsNumber = "";
    		Booking curBooking = null;
    		curBooking = cellData.getValue();
    		
    		List<Room> bookedRooms = curBooking.getRooms();
    		for(Room r : bookedRooms) {
    			roomsNumber += r.getRoomNumber() + ", ";
    		}
    		if(roomsNumber.length()>0) {
    			roomsNumber = roomsNumber.substring(0, roomsNumber.length()-2);
    		}
            return new ReadOnlyStringWrapper(roomsNumber);
        });
	}

    @FXML
    private void checkIn(ActionEvent event) {
		System.out.println("Check In Process...");
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(currentUser);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		
		serviceDirector.checkInBooking(selectedBookings);
		loadBookingTable();
	}
    @FXML
    private void checkOut(ActionEvent event) {
		System.out.println("Check Out Process...");
		for(Booking b : selectedBookings) {
			if((b.getCheckInStatus()==null? false : b.getCheckInStatus())==false) {
				HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("checkingout.warning"), 
						AlertType.ERROR);
				return;
			}
		}
		
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(currentUser);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		
		serviceDirector.checkOutBooking(selectedBookings);
		loadBookingTable();
	}
    
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
