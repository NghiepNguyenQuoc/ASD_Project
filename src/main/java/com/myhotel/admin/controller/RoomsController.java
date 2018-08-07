package com.myhotel.admin.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.domain.Room;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.RoomServiceImpl;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
@Controller
public class RoomsController implements Initializable {

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

	private ObservableList<Room> roomListObservable = FXCollections.observableArrayList();
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setColumnProperties();
		loadRooms();
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
			if (roomVailable == true) {
				availableAsString = "Available";
			} else {
				availableAsString = "Occupated";
			}

			return new ReadOnlyStringWrapper(availableAsString);
		});

	}

	private void loadRooms() {

		List<Room> rooms = null;
		if (roomService == null) {
			roomService = ApplicationContextHolder.getContext().getBean(RoomServiceImpl.class);
		}
		rooms = roomService.findAvailableRoom();
		roomListObservable.clear();
		roomListObservable.addAll(rooms);
		roomTableView.setItems(roomListObservable);

	}
}
