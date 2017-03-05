package com.neu.edu.wtp.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthRegEx {

	private  Pattern pattern;
	private  Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public PasswordStrengthRegEx() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public  boolean validate(final String password) {
		matcher = pattern.matcher(password);
		System.out.println("Inside Password Utility matcher");
		return matcher.matches();

	}
}
