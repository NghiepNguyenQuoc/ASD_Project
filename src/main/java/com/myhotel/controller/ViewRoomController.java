package com.myhotel.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.myhotel.config.StageManager;
import com.myhotel.domain.*;
import com.myhotel.domain.booking.ConcreteServiceBuilder;
import com.myhotel.domain.booking.ServiceBuilder;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.patterns.FactoryMethod.ConcretePromotionFactory;
import com.myhotel.patterns.FactoryMethod.HolidayPromotion;
import com.myhotel.patterns.FactoryMethod.PromotionFactory;
import com.myhotel.patterns.Iterator.Iterator;
import com.myhotel.patterns.Iterator.RoomRepository;
import com.myhotel.repository.HotelUserRepository;
import com.myhotel.repository.UserRepository;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.PaymentService;
import com.myhotel.service.impl.RoomServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

@Controller
public class ViewRoomController extends ApplicationController implements Initializable, ChangeListener {

	public TableColumn<Room, String> colAdults;
	public TableColumn<Room, String> colChildren;
	public TableColumn<Room, String> colRoomType;
	public TableColumn<Room, String> colStatus;
	public TableColumn<Room, String> colTax;
    public TextField startDate;
	public TextField endDate;
	public TextField totalRoomsSelected;
	public TextField discount;
	public Button pay;
	public TextField searchBox;
	@Autowired
	RoomServiceImpl roomService;

	public List<Room> selectedRooms;

	@FXML
	public TableView<Room> roomTableView;
	@FXML
	public TableColumn<Room, String> colRoomNumber;
	@FXML
	public TableColumn<Room, String> colPrice;
	@FXML
	public TableColumn<Room, String> colBedType;
	@FXML
	private Label userId;
	@FXML
	private Button btnLogout;
	@FXML
	private Label userMess;
	
	@FXML
	private Button btnPrev;
	@FXML
	private Button btnNext;

	private ObservableList<Room> roomListObservable = FXCollections.observableArrayList();
	private String searchKeyword;
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

	private RoomRepository roomRepo = new RoomRepository();
	private Iterator iterator;
	private int currentIndex = 0;

	@FXML
    private void exit(ActionEvent event) {
		
	}
	
	@Lazy
    @Autowired
    private StageManager stageManager;

	public TextField getStartDate() {
		return startDate;
	}

	public void setStartDate(TextField startDate) {
		this.startDate = startDate;
	}

	@FXML
    private void pay(ActionEvent event) {

		System.out.println("Pay button pressed");
		List<Room> listSelectedRooms = getSelectedRooms();

		String endDate = this.endDate.getText();
		String startDate = this.startDate.getText();
		if (listSelectedRooms!=null&&listSelectedRooms.size()>0){
			int totalRoomSelected = Integer.parseInt(this.totalRoomsSelected.getText());
			this.totalRoomsSelected.setText(totalRoomSelected+"");
		}
		if (currentUser!=null){
			List<Promotion> promotions = currentUser.getPromotions();
			if (promotions != null && promotions.size()>0){
				this.discount.setText(promotions.get(0).getName());
			}
		}
		System.out.println();

		// Prepare data to send to booking layout
		ServiceBuilder serviceBuilder = new ConcreteServiceBuilder(currentUser);
		ServiceDirector serviceDirector = new ServiceDirector(serviceBuilder);
		serviceDirector.createBooking(startDate, endDate);
		serviceDirector.setRoomsToBooking(listSelectedRooms);
		
		// Go to booking layout
		goToBookingLayout(serviceDirector);
	}
	
	@FXML
    private void moveNext(ActionEvent event) {

		System.out.println("move Next button pressed");
		
		if(iterator.hasNext()) {
			
			iterator.next();
			
			currentIndex++;
			
			roomTableView.getSelectionModel().clearSelection();
			roomTableView.getSelectionModel().select(currentIndex);
			roomTableView.getFocusModel().focus(currentIndex);
		}		
	}
	
	@FXML
    private void movePrev(ActionEvent event) {

		System.out.println("move Prev button pressed");
		
		if(iterator.hasPrev()) {
			
			iterator.previous();
			
			currentIndex--;
			
			roomTableView.getSelectionModel().clearSelection();
			roomTableView.getSelectionModel().select(currentIndex);
			roomTableView.getFocusModel().focus(currentIndex);
		}
	}

	public void loadRoomTable(boolean withKeyword){
		List<Room> rooms=null;
		if (roomService == null){
			roomService = ApplicationContextHolder.getContext().getBean(RoomServiceImpl.class);
		}
		if (withKeyword){
			rooms = roomService.findAvailableRoomByKeyword(this.searchKeyword);
		}else {
			rooms = roomService.findAvailableRoom();
		}
		
		iterator = roomRepo.getIterator(this.searchKeyword);
		currentIndex = 0;
		
		roomListObservable.clear();
		roomListObservable.addAll(rooms);
		roomTableView.setItems(roomListObservable);

		loadStartDateEndDate();
		loadPromotion();
		
		roomTableView.requestFocus();
		roomTableView.getSelectionModel().select(currentIndex);
		roomTableView.getFocusModel().focus(currentIndex);
	}

	public void loadPromotion(){
		HotelUserRepository userRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);

		HotelUser hotelUser = userRepository.findOne(currentUser.getId());
		List<Promotion> promotions = hotelUser.getPromotions();
		int discountTotal = 0;
		for (Promotion promotion: promotions){
			PromotionFactory promotionFactory = new ConcretePromotionFactory();

			discountTotal += promotionFactory.createPromotion(promotion.getName()).executeGetDiscout();
		}
		discount.setText(discountTotal+"");
	}

	public void loadStartDateEndDate(){
		Date dt = getDaysFromNow(1);
		startDate.setText(dateFormat.format(getDaysFromNow(1)));
		endDate.setText(dateFormat.format(getDaysFromNow(2)));
	}

	public Date getDaysFromNow(int i){
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, i);
		dt = c.getTime();
		return dt;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		// Update message to say hello to user
		if (currentUser != null) {
			userMess.setText("Hello " + currentUser.getFirstName() + " !");
		}
		
        roomTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        addSelectionListener();

		loadRoomTable(false);
	}

    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(List<Room> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    public void addSelectionListener(){
        roomTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRooms = roomTableView.getSelectionModel().getSelectedItems();
			this.totalRoomsSelected.setText(selectedRooms.size()+"");
                });
        searchBox.textProperty().addListener(this);

	}


    private void setColumnProperties() {
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
		colAdults.setCellValueFactory(new PropertyValueFactory<>("numberAdult"));
		colChildren.setCellValueFactory(new PropertyValueFactory<>("numberChildren"));
		colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
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

	}

	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		System.out.println(newValue);
		this.searchKeyword = newValue.toString();
		if (newValue.toString().equals("")){
			loadRoomTable(false);
		}else {
			loadRoomTable(true);
		}
	}
}
