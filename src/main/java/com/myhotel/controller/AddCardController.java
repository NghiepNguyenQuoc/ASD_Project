package com.myhotel.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myhotel.domain.Card;
import com.myhotel.domain.DebitCard;
import com.myhotel.domain.Payment;
import com.myhotel.domain.booking.ConcreteServiceBuilder;
import com.myhotel.domain.booking.ServiceDirector;
import com.myhotel.domain.cardfactory.CardFactory;
import com.myhotel.domain.cardfactory.SimpleCardFactory;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.CardService;
import com.myhotel.service.PaymentService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
