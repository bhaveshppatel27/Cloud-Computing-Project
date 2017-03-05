package com.neu.edu.wtp.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.wtp.pojo.User;
import com.neu.edu.wtp.utility.PasswordStrengthRegEx;

public class PasswordValidator implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if(user==null){
			
			errors.reject("Invalid.UserName.Password");
		}
		PasswordStrengthRegEx  pwdRegex=new PasswordStrengthRegEx();
		boolean isValid=pwdRegex.validate(user.getPassword());
		if(!isValid){
			errors.rejectValue("password", "Password.Invalid");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.user", "Password Required");
		
	}
}
