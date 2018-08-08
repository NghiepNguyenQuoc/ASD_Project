package com.myhotel.admin.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public abstract class ChildController {
	private HomeController parentController;

	public HomeController getParentController() {
		return parentController;
	}

	public void setParentController(HomeController parentController) {
		this.parentController = parentController;
	}

	public void loadView(String viewFile) {
		BorderPane rootPane = getParentController().getRootPane();
		URL viewUrl = getClass().getResource(viewFile);
		try {
			FXMLLoader loader = new FXMLLoader();
			Node content = loader.load(viewUrl.openStream());
			if (loader.getController() instanceof ChildController) {
				ChildController childController = (ChildController)loader.getController();
				childController.setParentController(parentController);
			}
			
			rootPane.setCenter(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
