package com.ibm.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCountryException extends RuntimeException {
	private Logger logger = LoggerFactory.getLogger(InvalidIpException.class);

	public InvalidCountryException() {
		super();
	}

	public InvalidCountryException(String message) {
		super(message);

		logger.warn(message);
	}
}
