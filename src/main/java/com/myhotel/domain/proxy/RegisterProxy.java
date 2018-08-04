package com.myhotel.domain.proxy;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.myhotel.config.StageManager;
import com.myhotel.controller.ApplicationController;
import com.myhotel.domain.Address;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.Payment;
import com.myhotel.domain.alert.HotelAlert;
import com.myhotel.domain.validator.HotelValidator;
import com.myhotel.service.AddressService;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.CardService;
import com.myhotel.service.HotelUserService;
import com.myhotel.service.PaymentService;
import com.myhotel.view.FxmlView;


public class RegisterProxy {
    @Autowired
    public StageManager stageManager;

	@Autowired
	private HotelUserService hotelUserService; 
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PaymentService paymentService;
	
	public void save(HotelUser entity) {
		if(HotelValidator.validate("Email", entity.getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
			HotelValidator.validate("First Name", entity.getFirstName(), "[a-zA-Z]+") &&
			HotelValidator.validate("Last Name", entity.getLastName(), "[a-zA-Z]+") && 
			HotelValidator.emptyValidation("Password", entity.getPassword().isEmpty())
    	   ){ 
			
			addressService = ApplicationContextHolder.getContext().getBean(AddressService.class);
			entity.setAddress(addressService.save(entity.getAddress()));


			paymentService = ApplicationContextHolder.getContext().getBean(PaymentService.class);
			Payment payment = new Payment();
			paymentService.save(payment);
			
			entity.setPayment(payment);
			
			hotelUserService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
			hotelUserService.save(entity); 
			
			HotelAlert.showAlert(ResourceBundle.getBundle("Bundle").getString("register.successful"));
			
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
			stageManager.switchScene(FxmlView.LOGIN);
		}
	}
	
}
