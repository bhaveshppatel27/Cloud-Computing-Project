package com.neu.edu.wtp.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.wtp.pojo.Transaction;
import com.neu.edu.wtp.pojo.User;

public class BankValidator  implements Validator {

	public boolean supports(@SuppressWarnings("rawtypes") Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		Transaction transaction = (Transaction) obj;
		
		if(transaction.getAmount()<1){
			errors.reject("Amount.Negative");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "error.invalid.transaction", "Amount Required");
		
		
	}
}
