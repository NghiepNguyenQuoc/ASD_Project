package com.myhotel.admin.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Booking;
import com.myhotel.domain.Promotion;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.PromotionService;
import com.myhotel.service.impl.BookingServiceImpl;
import com.myhotel.service.impl.PromotionServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class PromotionController extends ApplicationController implements Initializable {
	public List<Promotion> promotions;
	
	@Autowired
	PromotionService promotionService;
	private ObservableList<Promotion> promotionObservable = FXCollections.observableArrayList();
	
//	@FXML
//	private TableView<Promotion> tablePromotion;
	
	@FXML
	private TableColumn<Promotion, Integer> colID;

	@FXML
    private TableColumn<Promotion, String> colName;
	@FXML
    private TableColumn<Promotion, Integer> colDiscount;
	
	@FXML
    private TableColumn<Promotion, Float> colPercent;

	@FXML
	private Button btnCreate;
	
	
	
	public List<Booking> selectedBookings;
	@Autowired
	BookingServiceImpl bookingService;
	private ObservableList<Booking> bookingListObservable = FXCollections.observableArrayList();
	private TableView<Booking> tablePromotion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		setColumnProperties();
		addSelectionListener();
		loadBookingTable();
	}

	public void addSelectionListener(){
		tablePromotion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedBookings = tablePromotion.getSelectionModel().getSelectedItems();});
	}
	
	public void loadBookingTable(){
//		List<Promotion> promotions = null;
//		promotions = null;
//		if (promotionService==null)
//			promotionService = ApplicationContextHolder.getContext().getBean(PromotionServiceImpl.class);
//		
////		tablePromotion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		
//		promotions = promotionService.findAll();
////		promotions.removeIf(b -> (b.getCheckOutStatus()==null? false : b.getCheckOutStatus()) == true);
//		
//		promotionObservable.clear();
//		promotionObservable.addAll(promotions);
//		tablePromotion.setItems(promotionObservable);
		
		List<Booking> bookings = null;
		selectedBookings = null;
		if (bookingService==null)
			bookingService = ApplicationContextHolder.getContext().getBean(BookingServiceImpl.class);
		
		tablePromotion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		bookings = bookingService.findAll();
		bookings.removeIf(b -> (b.getCheckOutStatus()==null? false : b.getCheckOutStatus()) == true);
		
		bookingListObservable.clear();
		bookingListObservable.addAll(bookings);
		tablePromotion.setItems(bookingListObservable);
		
	}

    private void setColumnProperties() {
    	colID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colName.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
    	colDiscount.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
    	colPercent.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));

	}
}
