package mum.asd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import mum.asd.domain.HotelUser;
import mum.asd.domain.Promotion;
import mum.asd.repository.HotelUserRepository;
import mum.asd.service.ApplicationContextHolder;
import org.springframework.stereotype.Controller;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mum.asd.domain.Card;
import mum.asd.domain.Room;
import mum.asd.domain.alert.HotelAlert;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.domain.bookingprices.ServiceElementDoVisitor;

/**
 * @author vynguyen
 *
 */
@Controller
public class BookingController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;
	
	@FXML
	private TextField name;
	
	@FXML
	private Label userId;
	
	@FXML
	private TextField address;
	
	@FXML
	private ComboBox<String> cardNumber;
	
	@FXML
	private Button addNewCard;
	
	@FXML
	private TextField startDate;
	
	@FXML
	private TextField endDate;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button pay;
	
	@FXML
	private Button btnLogout;
	
	@FXML
	private TableView<Room> roomsTable;
	
	@FXML
	private TextField totalPrice;
	
	@FXML
	private TextField discount;
	
	@FXML
	private TableColumn<Room, Long> colRoomNumber;

	@FXML
	private TableColumn<Room, String> colPrice;

	@FXML
	private TableColumn<Room, LocalDate> colBedType;

	@FXML
	private TableColumn<Room, String> colAdults;
	
	@FXML
    private TableColumn<Room, String> colChildren;

	@FXML
	private TableColumn<Room, String> colRoomType;
	
	@FXML
    private TableColumn<Room, String> colStatus;
	
	@FXML
	private TableColumn<Room, String> colTax;
	
	@FXML
    private MenuItem deleteRoom;
	
	@FXML
	private Label userMess;

	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		goToViewRoomLayout();
	}
	
	@FXML
    private void pay(ActionEvent event) {
		if (this.cardNumber.getValue() != null) {
			this.serviceDirector.getServiceBuilder().saveBooking();
			HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("booking.completed"), 
					AlertType.INFORMATION);
			goToViewRoomLayout();
		} else {
			HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("booking.warning"), 
							AlertType.WARNING);
		}
	}
	public int loadPromotion(){
		HotelUserRepository userRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);

		HotelUser hotelUser = userRepository.findOne(currentUser.getId());
		List<Promotion> promotions = hotelUser.getPromotions();
		int discountTotal = 0;
		for (Promotion promotion: promotions){
			discountTotal += promotion.getDiscount();
		}
		discount.setText(discountTotal+"");
		return discountTotal;
	}
	
	@FXML
    private void addNewCard(ActionEvent event) {
		gotoAddCardLayout(this.serviceDirector);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// Update message to say hello to user
		if (currentUser != null) {
			userMess.setText("Hello " + currentUser.getFirstName() + " !");
		}
	}

	// Use for passing data from view book controller to booking controller
	public void setServiceDirector(ServiceDirector serviceDirector) {
		
		// Using builder pattern to do booking action
		this.serviceDirector = serviceDirector;
		ConcreteServiceBuilder concreteServiceBuilder =
						(ConcreteServiceBuilder)this.serviceDirector.getServiceBuilder();
		
		// Show user information to GUI
		this.name.setText(concreteServiceBuilder.getUser().getFirstName() + 
				" " + concreteServiceBuilder.getUser().getLastName());
		this.address.setText(concreteServiceBuilder.getUser().getAddress().toString());
		
		// Show list cards of user if have
		List<String> numCard = new ArrayList<>();
		for (Card c : concreteServiceBuilder.getUser().getPayment().getCards()) {
			String cardNumber = c.getCardNumber();
			numCard.add("xxxxxx" + cardNumber.substring(0, cardNumber.length() - 5));
		}
		
		this.cardNumber.getItems().addAll(numCard);
		this.startDate.setText(concreteServiceBuilder.getBooking().getStartDate_S());
		this.endDate.setText(concreteServiceBuilder.getBooking().getEndDate_S());
		
		List<Room> lstRoom = concreteServiceBuilder.getBooking().getRooms();
//		concreteServiceBuilder.getBooking().
		Double discountPercent = 0.0;
		// Verify discount
		if (lstRoom.size() > 2 && lstRoom.size() < 5) {
			discountPercent = 0.1;
		} else if (lstRoom.size() > 5) {
			discountPercent = 0.3;
		}
		this.discount.setText(String.valueOf(discountPercent));
		
		// Count total price using visitor pattern
		ServiceElementDoVisitor serviceElementVisitor = new ServiceElementDoVisitor();
		for (Room r : lstRoom) {
			r.accept(serviceElementVisitor);
		}
		double finalPrice = serviceElementVisitor.getPrice();
		if (discountPercent > 0) {
			finalPrice = finalPrice - (finalPrice * discountPercent);
		}
		finalPrice = finalPrice - (finalPrice * (float)loadPromotion()/100);

		this.totalPrice.setText(String.valueOf(finalPrice));
		
		
		// Load list room to table view
		ObservableList<Room> data = FXCollections.observableArrayList(lstRoom);
		colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
		colAdults.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
		colChildren.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
		colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		colStatus.setCellValueFactory(cellData -> {
            boolean roomVailable = cellData.getValue().isRoomVailable();
            String availableAsString;
            if(roomVailable == true)
            {
                availableAsString = "Available";
            }
            else
            {
                availableAsString = "Occupated";
            }

            return new ReadOnlyStringWrapper(availableAsString);
        });
		colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		
		data.clear();
		data.addAll(lstRoom);
		roomsTable.setItems(data);
		
	}
	
	public ServiceDirector getServiceDirector() {
		return this.serviceDirector;
	}
}
