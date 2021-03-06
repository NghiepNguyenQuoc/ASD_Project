package com.myhotel.controller;

import static com.myhotel.controller.ApplicationController.currentUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.myhotel.config.StageManager;
import com.myhotel.domain.HotelUser;
import com.myhotel.domain.User;
import com.myhotel.domain.UserType;
import com.myhotel.logging.ChainPatternLogging;
import com.myhotel.patterns.COR.AbstractLogger;
import com.myhotel.patterns.FactoryMethod.PromotionName;
import com.myhotel.patterns.Mediator.ConcreteHotelCustomer;
import com.myhotel.patterns.Mediator.ConcreteLoginCustomer;
import com.myhotel.patterns.Mediator.HotelCustomer;
import com.myhotel.patterns.Mediator.LoginCustomer;
import com.myhotel.patterns.Mediator.PromotionMediator;
import com.myhotel.patterns.Mediator.PromotionMediatorImpl;
import com.myhotel.patterns.Mediator.ValidationMediator;
import com.myhotel.patterns.Mediator.ValidationMediatorImpl;
import com.myhotel.repository.HotelUserRepository;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.HotelUserService;
import com.myhotel.service.UserService;
import com.myhotel.service.impl.AddressServiceImpl;
import com.myhotel.service.impl.SampleDataService;
import com.myhotel.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label lblLogin;

	@Autowired
	private HotelUserService hotelUserService;

	@Autowired
	AddressServiceImpl addressService;

	@Autowired
	SampleDataService sampleDataService;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private void loginAction(ActionEvent event) throws IOException {
		try {
			
			HotelUser checkingUser = new HotelUser();
			checkingUser.setEmail(getUsername());
			checkingUser.setPassword(getPassword());
			
			LoginCustomer loginCustomer = new ConcreteLoginCustomer(validationMediator, checkingUser);
			
			if(!validationMediator.isValidHotelCustomer(loginCustomer)) {
				lblLogin.setText("User Name or Password are not correct.");
				loggerChain.logMessage(AbstractLogger.INFO, "User Name or Password are not correct.");
				return;
			}
			
			if (hotelUserService.authenticate(getUsername(), getPassword())) {
				HotelUser user = hotelUserService.findByEmail(getUsername());
				if (user.getUserType() == UserType.Admin) {
					stageManager.switchScene(FxmlView.ADMIN_HOME);
				} else {
					stageManager.switchScene(FxmlView.VIEWROOMS);
				}

				loggerChain.logMessage(AbstractLogger.INFO, "Login successfully.");

			} else {
				lblLogin.setText("Login Failed.");

				loggerChain.logMessage(AbstractLogger.INFO, "Login failed.");
			}
		} catch (Exception e) {
			loggerChain.logMessage(AbstractLogger.ERROR, "Error Message: " + e.getMessage() + ", StackTrace: " + e.getStackTrace());
		}
	}

	@FXML
	private void btnRegister(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.REGISTER);
	}

	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		String text = username.getText();
		if (!text.contains("@")) {
			text = text + "@gmail.com";
		}
		return text;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sampleMethod();

		loggerChain = ChainPatternLogging.getChainOfLoggers();
		setupValidationMediator();
	}

	List<HotelUser> hotelUsers;
	PromotionMediator promotionMediator;
	HotelCustomer currentHotelCustomer;
	boolean didSetUpMediator = false;
	AbstractLogger loggerChain;
	
	HotelUser loginUser;
	ValidationMediator validationMediator;

	public void sampleMethod() {
		sampleDataService.generateSampleData();
	}

	public void setupPromotionMediator() {
		HotelUserRepository hotelUserRepository = ApplicationContextHolder.getContext()
				.getBean(HotelUserRepository.class);
		hotelUsers = hotelUserRepository.findAll();
		promotionMediator = new PromotionMediatorImpl();
		for (HotelUser hotelUser : hotelUsers) {
			HotelCustomer hotelCustomer = new ConcreteHotelCustomer(promotionMediator, hotelUser);
			if (currentUser.getId() == hotelUser.getId()) {
				currentHotelCustomer = hotelCustomer;
			}
			promotionMediator.addHotelCustomer(hotelCustomer);
		}
	}
	
	public void setupValidationMediator() {		
		validationMediator = new ValidationMediatorImpl();
	}

	@Scheduled(cron = "0/45 * * * * *")
	public void broadCastPromotionToHotelUser() {
		if (currentUser != null) {
			if (didSetUpMediator == false) {
				setupPromotionMediator();
				didSetUpMediator = true;
			}
			currentHotelCustomer.sendPromotion(PromotionName.SpringHoliday.toString());
		}
	}
}
