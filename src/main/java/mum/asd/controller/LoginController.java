package mum.asd.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import mum.asd.patterns.FactoryMethod.PromotionName;
import mum.asd.patterns.Mediator.ConcreteHotelCustomer;
import mum.asd.patterns.Mediator.HotelCustomer;
import mum.asd.patterns.Mediator.PromotionMediator;
import mum.asd.patterns.Mediator.PromotionMediatorImpl;
import mum.asd.repository.HotelUserRepository;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.impl.AddressServiceImpl;
import mum.asd.service.impl.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import mum.asd.config.StageManager;
import mum.asd.service.HotelUserService;
import mum.asd.service.UserService;
import mum.asd.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static mum.asd.controller.ApplicationController.currentUser;

/**
 * @author Tan Tho Nguyen
 */

@Controller
public class LoginController implements Initializable{

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
    private void loginAction(ActionEvent event) throws IOException{
    	if(hotelUserService.authenticate(getUsername(), getPassword())){
    		    		
    		stageManager.switchScene(FxmlView.VIEWROOMS);
    		
    	}else{
    		lblLogin.setText("Login Failed.");
    	}
    }
	
	@FXML
    private void btnRegister(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.REGISTER);
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		String text=username.getText();
		if (!text.contains("@")){
			text = text +"@gmail.com";
		}
		return text;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sampleMethod();

	}
	List<HotelUser> hotelUsers;
	PromotionMediator promotionMediator;
	HotelCustomer currentHotelCustomer;
	boolean didSetUpMediator = false;
	public void sampleMethod(){
		sampleDataService.generateSampleData();
	}
	public void setupPromotionMediator(){
		HotelUserRepository hotelUserRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);
		hotelUsers = hotelUserRepository.findAll();
		promotionMediator = new PromotionMediatorImpl();
		for (HotelUser hotelUser: hotelUsers){
			HotelCustomer hotelCustomer = new ConcreteHotelCustomer(promotionMediator,hotelUser);
			if (currentUser.getId() == hotelUser.getId()){
				currentHotelCustomer = hotelCustomer;
			}
			promotionMediator.addHotelCustomer(hotelCustomer);
		}
	}

//	@Scheduled(cron="0/2 * * * * *")
	public void printHello(){
		System.out.println("Hello World");
	}

	@Scheduled(cron="0/45 * * * * *")
	public void broadCastPromotionToHoterlUser() {
		if(currentUser!=null){
			if (didSetUpMediator == false){
				setupPromotionMediator();
				didSetUpMediator = true;
				System.out.println("did setup Mediator");
			}
			currentHotelCustomer.sendPromotion(PromotionName.SpringHoliday.toString());
//			promotionMediator.broadCastPromotion(PromotionName.SpringHoliday.toString(),currentHotelCustomer);
			System.out.println("BroadCast holiday promotion");
		}
	}
}
