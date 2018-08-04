package com.myhotel.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HotelValidator {
	/*
	 * Validations
	 * Tan Tho Nguyen
	 */
	public static boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	public static boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }
	
	public static boolean validatePassword(String field, String field2){
        if(field.equals(field2)){
            return true;
        }else{
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("Password and Confirm Password are NOT match ");
        	alert.showAndWait();
            return false;            
        }
    }
	
	public static void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
}
