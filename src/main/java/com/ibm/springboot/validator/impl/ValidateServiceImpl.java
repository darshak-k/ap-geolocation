package com.ibm.springboot.validator.impl;

import java.util.function.IntPredicate;

import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.exception.ExceptionConstantMessage;
import com.ibm.springboot.exception.InvalidIpException;
import com.ibm.springboot.exception.InvalidPasswordException;
import com.ibm.springboot.exception.InvalidUsername;
import com.ibm.springboot.validator.ValidationService;

@Service
public class ValidateServiceImpl implements ValidationService {

	@Override
	public void validate(GeoAPIRequest request) throws Exception {
		validateUserName(request.getUsername());
		validateIp(request.getIP());
		validatePassword(request.getPassword());

	}

	private void validatePassword(String password) throws Exception {
		// TODO Auto-generated method stub
		if (password == null || password.length() == 0) {
			throw new InvalidPasswordException(ExceptionConstantMessage.BLANK_PASSOWRD);
		}

		StringBuilder errorMessage = new StringBuilder();

		// TODO Auto-generated method stub

		if (password.length() < 8) {
			errorMessage.append(ExceptionConstantMessage.PASSOWORD_LENGTH_EXCEPTION);
		}

		if (!containsLowerCase(password)) {
			errorMessage.append(ExceptionConstantMessage.NOT_CONTAINS_LOWERCASE_LETTER);
		}

		if (!containsUpperCase(password)) {
			errorMessage.append(ExceptionConstantMessage.NOT_CONTAINS_UPPERCASE_LETTER);
		}

		if (!containsNumber(password)) {
			errorMessage.append(ExceptionConstantMessage.NOT_CONTAINS_DIGITS);
		}

		if (!containsSpecialCharacter(password)) {
			errorMessage.append(ExceptionConstantMessage.NOT_CONTAINS_SPECIAL_LETTER);
		}
	}

	private void validateIp(String ip) throws Exception {
		// TODO Auto-generated method stub
		if (ip == null || ip.length() == 0) {
			throw new InvalidIpException(ExceptionConstantMessage.BLANK_IP);
		}

	}

	private void validateUserName(String username) throws Exception {
		// TODO Auto-generated method stub
		if (username == null || username.length() == 0) {
			throw new InvalidUsername(ExceptionConstantMessage.BLANK_USERNAME);
		}
	}

	private boolean contains(String value, IntPredicate predicate) {
		return value.chars().anyMatch(predicate);
	}

	private boolean containsLowerCase(String value) {
		return contains(value, i -> Character.isLetter(i) && Character.isLowerCase(i));
	}

	private boolean containsUpperCase(String value) {
		return contains(value, i -> Character.isLetter(i) && Character.isUpperCase(i));
	}

	private boolean containsSpecialCharacter(String value) {
		return contains(value, i -> !Character.isLetterOrDigit(i));
	}

	private boolean containsNumber(String value) {
		return contains(value, Character::isDigit);
	}

}
