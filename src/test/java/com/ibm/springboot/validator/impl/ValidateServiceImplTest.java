package com.ibm.springboot.validator.impl;

import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.exception.InvalidIpException;
import com.ibm.springboot.exception.InvalidPasswordException;
import com.ibm.springboot.exception.InvalidUsername;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateServiceImplTest {

	private final ValidateServiceImpl validator = new ValidateServiceImpl();

	@Test
	void testValidUsername() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "P@ssw0rd", "192.168.1.1");
		assertDoesNotThrow(() -> validator.validate(request));
	}

	@Test
	void testBlankUsername() {
		GeoAPIRequest request = new GeoAPIRequest("", "P@ssw0rd", "192.168.1.1");
		assertThrows(InvalidUsername.class, () -> validator.validate(request));
	}

	@Test
	void testNullUsername() {
		GeoAPIRequest request = new GeoAPIRequest(null, "P@ssw0rd", "192.168.1.1");
		assertThrows(InvalidUsername.class, () -> validator.validate(request));
	}

	@Test
	void testValidIp() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "P@ssw0rd", "192.168.1.1");
		assertDoesNotThrow(() -> validator.validate(request));
	}

	@Test
	void testBlankIp() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "P@ssw0rd", "");
		assertThrows(InvalidIpException.class, () -> validator.validate(request));
	}

	@Test
	void testNullIp() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "P@ssw0rd", null);
		assertThrows(InvalidIpException.class, () -> validator.validate(request));
	}

	@Test
	void testValidPassword() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "P@ssw0rd", "192.168.1.1");
		assertDoesNotThrow(() -> validator.validate(request));
	}

	@Test
	void testShortPassword() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "short", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testPasswordWithoutLowerCase() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "PASSWORD123", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testPasswordWithoutUpperCase() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "password123", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testPasswordWithoutNumber() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "Password", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testPasswordWithoutSpecialCharacter() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "Password123", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testBlankPassword() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", "", "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}

	@Test
	void testNullPassword() {
		GeoAPIRequest request = new GeoAPIRequest("validUsername", null, "192.168.1.1");
		assertThrows(InvalidPasswordException.class, () -> validator.validate(request));
	}
}
