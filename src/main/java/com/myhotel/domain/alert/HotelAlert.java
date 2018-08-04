package com.myhotel.domain.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HotelAlert {
	
	public static void showAlert(String message){
		showAlert(message, AlertType.INFORMATION);
	}
	
	
	public static void showAlert(String message, AlertType type){
		Alert alert = new Alert(type);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
