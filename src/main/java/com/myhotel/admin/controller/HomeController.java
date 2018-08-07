package com.myhotel.admin.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.myhotel.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller
public class HomeController implements Initializable {
	/**
	 * If you assign a fx:id to the <fx:include> tag, FXMLLoader tries to inject the
	 * the controller of the included fxml to a field named <fx:id>Controller. You
	 * can pass the MainController reference to the child controllers in the
	 * initialize method:
	 */
	@FXML
	private MenusController menusController;

	@FXML
	private BorderPane rootPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menusController.setParentController(this);
		try {
			AnchorPane overview = FXMLLoader.load(getClass().getResource("/fxml/admin/overview.fxml"));
			rootPane.setCenter(overview);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public BorderPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}

}
