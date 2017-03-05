package com.neu.edu.wtp.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.edu.wtp.pojo.Customer;
import com.neu.edu.wtp.pojo.User;
import com.neu.edu.wtp.utility.PasswordStrengthRegEx;

public class CustomerValidator implements Validator {

	private PasswordStrengthRegEx passwordStrengthRegEx;

	public PasswordStrengthRegEx getPasswordStrengthRegEx() {
		return passwordStrengthRegEx;
	}

	public void setPasswordStrengthRegEx(PasswordStrengthRegEx passwordStrengthRegEx) {
		this.passwordStrengthRegEx = passwordStrengthRegEx;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		boolean isPhoneNumberValid=false;
		Customer customer = (Customer) obj;
		boolean isValid=passwordStrengthRegEx.validate(customer.getPassword());
		if (!isValid) {
			errors.rejectValue("password", "Password.Invalid");
		}
		try{
			Long.parseLong(Long.toString(customer.getPhoneNumber()));
			isPhoneNumberValid=true;
		}catch(java.lang.NumberFormatException ne){
			ne.printStackTrace();
			
		}
		
		if(!isPhoneNumberValid){
			errors.rejectValue("phoneNumber", "typeMismatch.phoneNumber");
		}

	}
}
