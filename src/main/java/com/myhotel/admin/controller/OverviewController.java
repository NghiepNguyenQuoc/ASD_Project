package com.myhotel.admin.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Card;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.Promotion;
import com.myhotel.domain.Room;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.domain.booking.ConcreteServiceBuilder;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.domain.bookingprices.ServiceElementDoVisitor;
import com.myhotel.repository.HotelUserRepository;
import com.myhotel.service.ApplicationContextHolder;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class OverviewController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Overview");
		
//		get booking list
		
//		weekly revenue (based on end date)
//		create chart by sending to ChartAdapter
//		add chart to GUI
		
		
	}
}
