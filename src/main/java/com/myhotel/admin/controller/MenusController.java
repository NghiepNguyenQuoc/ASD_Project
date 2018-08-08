package com.myhotel.admin.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.myhotel.config.StageManager;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller
public class MenusController extends ChildController {
	
	private StageManager stageManager;
	
	/**
	 * Event handler for MenuItem oveview
	 */
	@FXML
	void overview(ActionEvent event) {
		loadView(ViewsFiles.ADMIN_OVERVIEW);
	}

	@FXML
	void bookings(ActionEvent event) {
		System.out.println("bookings");
		loadView(ViewsFiles.ADMIN_BOOKINGS);
	}

	@FXML
	void promotions(ActionEvent event) {
		loadView(ViewsFiles.ADMIN_PROMOTIONS);
	}

	@FXML
	void rooms(ActionEvent event) {
		loadView(ViewsFiles.ADMIN_ROOMS);
	}

	@FXML
	void users(ActionEvent event) {
		loadView(ViewsFiles.ADMIN_USERS);
	}
	
	public void logout() {
		if (stageManager == null){
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
		}
    	stageManager.switchScene(FxmlView.LOGIN);
	}
}
