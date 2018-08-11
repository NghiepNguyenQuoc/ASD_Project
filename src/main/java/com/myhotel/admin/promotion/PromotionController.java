package com.myhotel.admin.promotion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Promotion;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.PromotionServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

	@FXML
	TextArea txtDescription;

	@FXML
	private VBox tableContainer;

	@FXML
	private AnchorPane formContainer;

	/**
	 * Originator
	 */
	private Promotion promotion;

	private CareTaker careTaker;

	private boolean eventRegistered;
	private boolean eventDisabled;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setColumnProperties();
		loadBookingTable();
		formContainer.setVisible(false);
		eventRegistered = false;
	}

	@FXML
	public void onNewPromotion() {
		promotion = Promotion.samplePromo.doClone();
		showFormData();
		tableContainer.setVisible(false);
		formContainer.setVisible(true);
		careTaker = new CareTaker();
		careTaker.addAction(new PromotionMemento(promotion.getName(), promotion.getDiscount(), promotion.getPercent(),
				promotion.getDescription()));
		if (!eventRegistered) {
			registerEvents();
			eventRegistered = true;
		}
	}

	private void showFormData() {
		eventDisabled = true;
		txtName.setText(promotion.getName());
		txtDiscount.setText(String.valueOf(promotion.getDiscount()));
		txtPercent.setText(String.valueOf(promotion.getPercent()));
		txtDescription.setText(promotion.getDescription());
		eventDisabled = false;
	}

	@FXML
	public void onCancel() {
		tableContainer.setVisible(true);
		formContainer.setVisible(false);
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
		captureData();
		promotionServiceImpl.save(promotion);
		tableContainer.setVisible(true);
		formContainer.setVisible(false);
		loadBookingTable();
		HotelAlert.showAlert("Promotion created");
	}

	private boolean captureData() {
		if (txtDiscount.getText() != null && txtPercent.getText() != null && txtName.getText() != null
				&& !txtDiscount.getText().isEmpty() && !txtPercent.getText().isEmpty()) {
			promotion.setDiscount(Integer.parseInt(txtDiscount.getText()));
			promotion.setPercent(Float.parseFloat(txtPercent.getText()));
			promotion.setName(txtName.getText());
			promotion.setDescription(txtDescription.getText());
			return true;
		}
		return false;
	}

	/**
	 * Capture GUI event to save state
	 */
	private void registerEvents() {
		eventDisabled = false;
		txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!eventDisabled) {
				careTaker.addAction(new PromotionMemento(promotion.getName(), promotion.getDiscount(),
						promotion.getPercent(), oldValue));
			}
		});

		txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!eventDisabled) {
				careTaker.addAction(new PromotionMemento(oldValue, promotion.getDiscount(), promotion.getPercent(),
						promotion.getDescription()));
			}
		});

		txtDiscount.textProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue != null && !oldValue.isEmpty()) {
				if (!eventDisabled) {
					careTaker.addAction(new PromotionMemento(promotion.getName(), Integer.parseInt(oldValue),
							promotion.getPercent(), promotion.getDescription()));
				}
			}
		});

		txtPercent.textProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue != null && !oldValue.isEmpty()) {
				if (!eventDisabled) {
					careTaker.addAction(new PromotionMemento(promotion.getName(), promotion.getDiscount(),
							Float.parseFloat(oldValue), promotion.getDescription()));
				}
			}
		});
	}

	@FXML
	public void onUndo() {
		System.out.println(careTaker);
		PromotionMemento memento = careTaker.undo();
		if (memento != null) {
			promotion.setValuesFromMemento(memento);
			showFormData();
		}
	}

}
