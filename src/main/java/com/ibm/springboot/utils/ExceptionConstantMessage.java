package com.ibm.springboot.utils;

public class ExceptionConstantMessage {

	public final static String BLANK_PASSOWRD = "Password is blank or empty.";
	public final static String PASSOWORD_LENGTH_EXCEPTION = "Password should be greater than 8 characters.";
	public final static String NOT_CONTAINS_LOWERCASE_LETTER = "Password is not containing Lower case letter.";
	public final static String NOT_CONTAINS_UPPERCASE_LETTER = "Password is not containing Upper case letter.";
	public final static String NOT_CONTAINS_DIGITS = "Password is not containing digits.";
	public final static String NOT_CONTAINS_SPECIAL_LETTER = "Password is not containing any special character.";

	public final static String BLANK_IP = "Ip is blank or empty.";
	public final static String BLANK_USERNAME = "Username is blank or empty.";

	private ExceptionConstantMessage() {

	}
}
