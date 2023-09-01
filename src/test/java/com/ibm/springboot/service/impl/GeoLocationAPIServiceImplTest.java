package com.ibm.springboot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import com.ibm.springboot.client.GeoLocationAPIClient;
import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.dto.GeoLocationAPIResponse;
import com.ibm.springboot.exception.InvalidCountryException;
import com.ibm.springboot.validator.ValidationService;

class GeoLocationAPIServiceImplTest {

	@Mock
	private GeoLocationAPIClient client;

	@Mock
	private ValidationService validateService;

	@InjectMocks
	private GeoLocationAPIServiceImpl geoLocationAPIService;

	@Value("${app.geoLocation.countryCode}")
	private String countryCode;

	@Value("${app.geoLocation.failResponseStatus}")
	private String failResponseStatus;

	private GeoAPIRequest geoLocationRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		geoLocationAPIService = new GeoLocationAPIServiceImpl(client, validateService);
	}

	@Test
	void testValidResponseAndCountryCode() throws Exception {
		geoLocationRequest = new GeoAPIRequest("username", "P@ssw0rd", "103.140.120.0");
		GeoLocationAPIResponse response = new GeoLocationAPIResponse();
		response.setCity("Burnaby");

		when(client.getIPDetailsByIp(geoLocationRequest.getIP())).thenReturn(response);

		GeoLocationAPICustomResponse customResponse = geoLocationAPIService.getGeoIPLocationDetails(geoLocationRequest);

		assertNotNull(customResponse);
		assertEquals(response.getCity(), customResponse.getCity());
	}

	@Test
	void testInvalidResponseStatus() {
		geoLocationRequest = new GeoAPIRequest("username", "P@ssw0rd", "192.168.1.1");
		GeoLocationAPIResponse response = new GeoLocationAPIResponse();
		response.setStatus(failResponseStatus);
		when(client.getIPDetailsByIp(geoLocationRequest.getIP())).thenReturn(response);

		assertThrows(InvalidCountryException.class,
				() -> geoLocationAPIService.getGeoIPLocationDetails(geoLocationRequest));
	}

	@Test
	void testInvalidCountryCode() {
		geoLocationRequest = new GeoAPIRequest("username", "P@ssw0rd", "192.168.1.1");
		GeoLocationAPIResponse response = new GeoLocationAPIResponse();
		response.setCountryCode("US"); // Different from configured countryCode
		when(client.getIPDetailsByIp(geoLocationRequest.getIP())).thenReturn(response);

		assertThrows(InvalidCountryException.class,
				() -> geoLocationAPIService.getGeoIPLocationDetails(geoLocationRequest));
	}
}
