package com.myhotel.admin.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Booking;
import com.myhotel.domain.Promotion;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.PromotionService;
import com.myhotel.service.impl.BookingServiceImpl;
import com.myhotel.service.impl.PromotionServiceImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class PromotionController extends ApplicationController implements Initializable {
	public List<Promotion> promotions;

	@Autowired
	PromotionServiceImpl promotionServiceImpl;
	private ObservableList<Promotion> promotionObservable = FXCollections.observableArrayList();

	@FXML
	private TableView<Promotion> tablePromotion;

	@FXML
	private TableColumn<Promotion, Long> colID;

	@FXML
	private TableColumn<Promotion, String> colName;
	@FXML
	private TableColumn<Promotion, Integer> colDiscount;

	@FXML
	private TableColumn<Promotion, Float> colPercent;

	@FXML
	private Button btnCreate;
	@FXML 
	private TitledPane createPane;
	@FXML 
	private TextField txtDiscount;
	@FXML 
	private TextField txtPercent;
	@FXML 
	private TextField txtName;
	
	private Promotion promotion;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setColumnProperties();
		loadBookingTable();
		createPane.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	promotion=Promotion.samplePromo.doClone();
            	txtName.setText(promotion.getName());
            	txtDiscount.setText(String.valueOf(promotion.getDiscount()));
            	txtPercent.setText(String.valueOf(promotion.getPercent()));
            }
        });
	}

	public void loadBookingTable() {
		List<Promotion> promotions = null;
		promotions = null;
		if (promotionServiceImpl == null)
			promotionServiceImpl = ApplicationContextHolder.getContext().getBean(PromotionServiceImpl.class);
		promotions = promotionServiceImpl.findAll();
		promotionObservable.clear();
		promotionObservable.addAll(promotions);
		tablePromotion.setItems(promotionObservable);
	}

	private void setColumnProperties() {
		colID.setCellValueFactory(new PropertyValueFactory<Promotion, Long>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Promotion, String>("name"));
		colDiscount.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("discount"));
		colPercent.setCellValueFactory(new PropertyValueFactory<Promotion, Float>("percent"));

	}
	@FXML
	public void onCreate(ActionEvent event) {
		promotion.setDiscount(Integer.parseInt(txtDiscount.getText()));
		promotion.setPercent(Float.parseFloat(txtPercent.getText()));
		promotion.setName(txtName.getText());
		promotionServiceImpl.save(promotion);
		loadBookingTable();
		HotelAlert.showAlert("Promotion created");
	}
}
