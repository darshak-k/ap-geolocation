package com.ibm.springboot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;

import com.ibm.springboot.client.GeoLocationAPIClient;
import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.dto.GeoLocationAPIResponse;
import com.ibm.springboot.exception.InvalidCountryException;
import com.ibm.springboot.validator.ValidationService;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GeoLocationAPIServiceImplTest {

	@Mock
	private GeoLocationAPIClient client;

	@Mock
	private ValidationService validateService;

	@InjectMocks
	private GeoLocationAPIServiceImpl geoLocationAPIService;

	private GeoAPIRequest geoLocationRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		geoLocationAPIService = new GeoLocationAPIServiceImpl(client, validateService, "CA", "fail");
	}

	@Test
	void testValidResponseAndCountryCode() throws Exception {
		geoLocationRequest = new GeoAPIRequest("username", "P@ssw0rd", "103.140.120.0");
		GeoLocationAPIResponse response = new GeoLocationAPIResponse();
		response.setCountryCode("CA");
		response.setStatus("success");
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
		response.setStatus("fail");
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
