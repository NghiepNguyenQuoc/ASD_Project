package mum.asd.controller;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.config.StageManager;
import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.view.FxmlView;
import mum.asd.domain.User;
import mum.asd.domain.booking.ServiceDirector;

public class ApplicationController {
	public static HotelUser currentUser = null;
	
	@Lazy
    @Autowired
    public StageManager stageManager;
	
	/*
	 * Show user info
	 * Tan Tho Nguyen
	 */
	
	
	public void showUserInfo() {
		if (stageManager == null){
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
		}
		stageManager.switchScene(FxmlView.USERINFO);
	}


	/*
	 * Logout
	 * Tan Tho Nguyen
	 */
    public void logout() {
		if (stageManager == null){
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
		}
    	stageManager.switchScene(FxmlView.LOGIN);
	}
	

	
	/*
	 * Get String From Resource Bundle
	 * Tan Tho Nguyen
	 */
	public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
	
	/*
	 * VyNguyen
	 * Use for moving to Booking layout
	 */
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
	
	/*
	 * VyNguyen
	 * Use for moving to ViewRoom layout
	 */
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
	
	/*
	 * VyNguyen
	 * User for moving to AddCard layout
	 */
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
