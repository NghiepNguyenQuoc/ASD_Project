package com.myhotel.domain.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HotelAlert {
	/*
	 * Show alert
	 * Tan Tho Nguyen
	 */
	public static void showAlert(String message){
		showAlert(message, AlertType.INFORMATION);
	}
	
	/*
	 * Show alert with specific type
	 * Vy Nguyen
	 */
	public static void showAlert(String message, AlertType type){
		Alert alert = new Alert(type);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
