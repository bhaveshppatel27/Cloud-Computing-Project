package com.neu.edu.wtp.Validators;


import org.joda.time.format.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.wtp.pojo.CreditCard;
import com.neu.edu.wtp.pojo.User;

public class CardValidator implements Validator{

	
	public boolean supports(@SuppressWarnings("rawtypes") Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		CreditCard creditCard = (CreditCard) obj;
		
		
		
		try{
			DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDate(creditCard.getValidFrom().toString());
		}catch(Exception e){
			errors.rejectValue("validFrom", "typeMismatch.java.util.Date");
			
		}
		
		boolean numberException=false;
		
		try{
			Long.parseLong(creditCard.getCardNumber().trim());
		}catch(Exception ie){
			ie.printStackTrace();
			numberException=true;
			
			
		}
		System.out.println(numberException);
		if(numberException){
			errors.rejectValue("cardNumber","Cardnumber.typemismatch");
			
//			if(creditCard.getCardNumber().equals("")||creditCard.getCardNumber().length()!=16){
//				errors.rejectValue("cardNumber","Cardnumber.Length");
//				
//			}
		}
		
		
		
		if(creditCard.getCvv()<100 || creditCard.getCvv()>1000){
			errors.rejectValue("cvv","Cvv.Length");
		}
		Integer.toString(creditCard.getPinCode());
		if(creditCard.getPinCode()<1000||creditCard.getPinCode()>9999){
			errors.rejectValue("pinCode","Pincode.Length");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "error.invalid.creditCard", "Card Number required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cvv", "error.invalid.creditCard", "CVV  required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pinCode", "error.invalid.creditCard", "Pincode  required");
		
		
		
	}

}
