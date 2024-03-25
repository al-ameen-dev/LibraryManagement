package com.alameendev.librarymanagement.validationutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	private static Validation validation;
	private final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private final String PHONE_REGEX = "^\\d{10}$";
	private final String NAME_REGEX = "^[A-Za-z\\s'-]+$";
	
	public static Validation validate() {
		if(validation==null) {
			validation = new Validation();
		}
		return validation;
	}
	
	public boolean email(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}
	
	public boolean name(String name) {
		Pattern pattern = Pattern.compile(NAME_REGEX);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	public boolean phone(String phone) {
		Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
	}
}
