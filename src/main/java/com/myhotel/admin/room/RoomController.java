package com.myhotel.admin.room;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.admin.controller.ChildController;
import com.myhotel.admin.controller.ViewsFiles;
import com.myhotel.domain.BedType;
import com.myhotel.domain.Room;
import com.myhotel.domain.RoomType;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.RoomServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
@Controller
public class RoomController extends ChildController implements Initializable {

	@Autowired
	private RoomServiceImpl roomService;

	@FXML
	private TextField roomNumber;

	@FXML
	private TextField price;

	@FXML
	private TextField adults;

	@FXML
	private TextField children;

	@FXML
	private ComboBox roomType;

	@FXML
	private ComboBox bedType;

	private Room room;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (roomService == null) {
			roomService = ApplicationContextHolder.getContext().getBean(RoomServiceImpl.class);
		}

		room = Room.sampleRoom.doClone();
		roomNumber.setText(room.getRoomNumber() + "");
		price.setText(String.format("%.00f", room.getPrice()));
		adults.setText(room.getNumberAdult() + "");
		children.setText(room.getNumberChildren() + "");
		ObservableList<RoomType> roomTypes = FXCollections.observableArrayList(RoomType.Standard, RoomType.Deluxe,
				RoomType.Suite);
		roomType.setItems(roomTypes);
		roomType.getSelectionModel().select(room.getRoomType());
		ObservableList<BedType> bedTypes = FXCollections.observableArrayList(BedType.Single, BedType.Double,
				BedType.Twin);
		bedType.setItems(bedTypes);
		bedType.getSelectionModel().select(room.getBedType());
	}

	@FXML
	public void onSave() {
		try {
			room.setRoomNumber(Integer.parseInt(roomNumber.getText()));
			room.setPrice(Float.parseFloat(price.getText()));
			room.setNumberAdult(Integer.parseInt(adults.getText()));
			room.setNumberChildren(Integer.parseInt(children.getText()));
			room.setRoomType((RoomType) roomType.getSelectionModel().getSelectedItem());
			room.setBedType((BedType) bedType.getSelectionModel().getSelectedItem());
			roomService.save(room);
			loadView(ViewsFiles.ADMIN_ROOMS);
			HotelAlert.showAlert("Room saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onCancel() {
		loadView(ViewsFiles.ADMIN_ROOMS);
	}
}
