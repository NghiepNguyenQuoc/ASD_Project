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
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.BookingServiceImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class CheckInController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;

	@Autowired
	BookingServiceImpl bookingService;
	private ObservableList<Booking> bookingListObservable = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Booking> bookingTableView;
	
	
	@FXML
	private TableColumn<Room, Long> colBookingNumber;

	@FXML
	private TableColumn<Room, LocalDate> colStartDate;

	@FXML
	private TableColumn<Room, LocalDate> colEndDate;

	@FXML
    private TableColumn<Booking, String> colRoomsNumber;

	
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
    	colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate_S"));
    	colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate_S"));

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

	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
