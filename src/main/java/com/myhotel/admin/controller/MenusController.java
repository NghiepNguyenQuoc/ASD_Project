package com.myhotel.admin.controller;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller
public class MenusController {

	private HomeController parentController;

	public HomeController getParentController() {
		return parentController;
	}

	public void setParentController(HomeController parentController) {
		this.parentController = parentController;
	}

	/**
	 * Event handler for MenuItem oveview
	 */
	@FXML
	void overview(ActionEvent event) {
		loadView(ViewsFiles.ADMIN_OVERVIEW);
	}

	private void loadView(String viewFile) {
		BorderPane rootPane = parentController.getRootPane();
		URL overviewUrl = getClass().getResource(viewFile);
		try {
			AnchorPane content = FXMLLoader.load(overviewUrl);
			rootPane.setCenter(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

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
}
