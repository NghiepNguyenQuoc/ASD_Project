package mum.asd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.asd.domain.Address;
import mum.asd.domain.Booking;
import mum.asd.domain.Card;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Room;
import mum.asd.domain.alert.HotelAlert;
import mum.asd.domain.userprofiletemplate.UserProfile;
import mum.asd.domain.userprofiletemplate.ViewUserInformation;
import mum.asd.domain.validator.HotelValidator;
import mum.asd.service.HotelUserService;
import mum.asd.service.impl.BookingServiceImpl;
import mum.asd.service.impl.RoomServiceImpl;
import mum.asd.view.FxmlView;

@Controller
public class UserInfoController extends ApplicationController implements Initializable{
			
	@FXML
	private TextField email;
	
	@FXML
    private TextField firstName;

	@FXML
    private TextField lastName;
	
	@FXML
    private TextField phone;
	
	@FXML
    private TextField gender;
	
	@FXML
    private PasswordField password;
	
	@FXML
    private PasswordField confirmPassword;
	
	@Autowired
	private HotelUserService hotelUserService;
	
	// Table column
	@FXML
	public TableView<Booking> bookingTableView;
	
	@FXML
	public TableColumn<Booking, String> col_booking_number;
	
	@FXML
	public TableColumn<Booking, String> col_start_date_s;
	
	@FXML
	public TableColumn<Booking, String> col_end_date_s;

	
	@FXML
	public TableView<Card> cardTableView;
	
	@FXML
	public TableColumn<Card, String> col_card_number;
	
	@FXML
	public TableColumn<Card, String> col_holdername;
	
	@Autowired
	BookingServiceImpl bookingService;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.email.setText(currentUser.getEmail());
		
		this.firstName.setText(currentUser.getFirstName());

		this.lastName.setText(currentUser.getLastName());
		
		this.phone.setText(currentUser.getPhone());
		
		this.password.setText(currentUser.getPassword());
		
		this.confirmPassword.setText(currentUser.getPassword());
		
		bookingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cardTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		setBookingColumnProperties();
		setCardColumnProperties();
		
		/*
		 * Template Method Design Pattern
		 * Tan Tho Nguyen
		 * Client
		 */
		UserProfile userProfile = new ViewUserInformation();
		userProfile.showProfileUser(currentUser, bookingTableView, cardTableView);
		
	}
	
	@FXML
    private void btnSave(ActionEvent event){

    	if(HotelValidator.validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
			HotelValidator.validate("First Name", getFirstName(), "[a-zA-Z]+") &&
			HotelValidator.validate("Last Name", getLastName(), "[a-zA-Z]+") && 
			HotelValidator.emptyValidation("Password", getPassword().isEmpty()) &&
			HotelValidator.emptyValidation("Confirm Password", getConfirmPassword().isEmpty()) &&
			HotelValidator.validatePassword(getPassword(), getConfirmPassword())
    	   ){
    
			currentUser.setEmail(getEmail());
			currentUser.setFirstName(getFirstName());
			currentUser.setLastName(getLastName());
			currentUser.setPhone(getPhone());
			currentUser.setPassword(getPassword());
			
			
			hotelUserService.save(currentUser);
			
			HotelAlert.showAlert(getStringFromResourceBundle("userinfo.successful"));
			
//			stageManager.switchScene(FxmlView.LOGIN);
    	}
    }
	
	@FXML
    private void btnCancel(ActionEvent event){
		stageManager.switchScene(FxmlView.VIEWROOMS);
	}
	
	
	private void setBookingColumnProperties() {
		col_booking_number.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
		col_start_date_s.setCellValueFactory(new PropertyValueFactory<>("startDate_S"));
		col_end_date_s.setCellValueFactory(new PropertyValueFactory<>("endDate_S"));
	}
	
	private void setCardColumnProperties() {
		col_card_number.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
		col_holdername.setCellValueFactory(new PropertyValueFactory<>("holdername"));
	}
	

	public String getEmail() {
		return email.getText();
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return lastName.getText();
	}

	public String getPhone() {
		return phone.getText();
	}

	public String getGender() {
		return gender.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getConfirmPassword() {
		return confirmPassword.getText();
	}
	
	
}
