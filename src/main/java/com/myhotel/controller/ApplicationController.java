package com.myhotel.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.myhotel.Main;
import com.myhotel.config.StageManager;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.view.FxmlView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationController {
	public static HotelUser currentUser = null;
	
	@Lazy
    @Autowired
    public StageManager stageManager;
	
	
	
	
	public void showUserInfo() {
		if (stageManager == null){
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
		}
		stageManager.switchScene(FxmlView.USERINFO);
	}


    public void logout() {
		if (stageManager == null){
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
		}
    	stageManager.switchScene(FxmlView.LOGIN);
	}
	

	
	
	public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
	
	
	public void goToBookingLayout(ServiceDirector serviceDirector) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Booking.fxml"));
			
			Parent root = (Parent)fxmlLoader.load();
			BookingController controller = fxmlLoader.<BookingController>getController();
			controller.setServiceDirector(serviceDirector);
			Scene scene = new Scene(root); 
			Stage stage = Main.getPrimaryStage();
			stage.setScene(scene);
			stage.setTitle(ResourceBundle.getBundle("Bundle").getString("booking.title"));
			
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void goToViewRoomLayout() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewRooms.fxml"));
			
			Parent root = (Parent)fxmlLoader.load();
			Scene scene = new Scene(root); 
			Stage stage = Main.getPrimaryStage();
			stage.setScene(scene);
			stage.setTitle(ResourceBundle.getBundle("Bundle").getString("viewrooms.title"));
			
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gotoAddCardLayout(ServiceDirector serviceDirector) {
		try {
			// call booking form
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AddCard.fxml"));
			
			Parent root = (Parent)fxmlLoader.load();
			
			AddCardController controller = fxmlLoader.<AddCardController>getController();
			controller.setServiceDirector(serviceDirector);
			Scene scene = new Scene(root); 
			Stage stage = Main.getPrimaryStage();
			stage.setScene(scene);
			stage.setTitle(ResourceBundle.getBundle("Bundle").getString("addcard.title"));

			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
