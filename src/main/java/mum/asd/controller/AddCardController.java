package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mum.asd.Main;
import mum.asd.domain.Card;
import mum.asd.domain.Payment;
import mum.asd.domain.booking.ConcreteServiceBuilder;
import mum.asd.domain.booking.ServiceDirector;
import mum.asd.domain.cardfactory.CardFactory;
import mum.asd.domain.cardfactory.SimpleCardFactory;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.CardService;
import mum.asd.service.PaymentService;

@Controller
public class AddCardController extends ApplicationController implements Initializable {
	private ServiceDirector serviceDirector;
	
	@FXML
	private Label lblLogin;
	
	@FXML
	private TextField cardHolder;
	
	@FXML
	private TextField cardNumber;
	
	@FXML
	private Button btnAddCard;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private TextField expDate;
	
	@FXML
	private TextField pinNumber;
	
	@FXML
	private ComboBox<String> cardType;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private PaymentService paymentService;
	
	@FXML
    private void addCard(ActionEvent event) {
		CardFactory cardFact = SimpleCardFactory.getFactory();
		Card card = cardFact.createCard(this.cardType.getValue());
		card.setCardNumber(this.cardNumber.getText());
		card.setHoldername(this.cardHolder.getText());
		card.setPinNumber(this.pinNumber.getText());
		
		String sDate = this.expDate.getText();
		card.setExpiredDateS(sDate);
		
		// Add card to database
		cardService = ApplicationContextHolder.getContext().getBean(CardService.class);
		cardService.save(card);
		
		// Add card to payment
		ConcreteServiceBuilder concreteServiceBuilder =
				(ConcreteServiceBuilder)this.serviceDirector.getServiceBuilder();
		Payment payment = concreteServiceBuilder.getUser().getPayment();
		payment.addCard(card);
		
		// Update payment to database
		paymentService = ApplicationContextHolder.getContext().getBean(PaymentService.class);
		paymentService.save(payment);
		
		// Back to booking layout
		goToBookingLayout(this.serviceDirector);
	}
	
	public void setServiceDirector(ServiceDirector serviceDirector) {
		this.serviceDirector = serviceDirector;
	}
	
	@FXML
    private void cancel(ActionEvent event) {
		// call booking form
		goToBookingLayout(this.serviceDirector);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.cardType.getItems().addAll("Normal", "Debit", "Credit");
	}
}
