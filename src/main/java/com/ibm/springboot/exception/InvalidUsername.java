package com.ibm.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUsername extends RuntimeException {
	private Logger logger = LoggerFactory.getLogger(InvalidUsername.class);

	public InvalidUsername() {
		super();
	}

	public InvalidUsername(String message) {
		super(message);

		logger.warn(message);
	}
}