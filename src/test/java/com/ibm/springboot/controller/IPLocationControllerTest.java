package com.ibm.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.service.impl.GeoLocationAPIServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class IPLocationControllerTest {

	@Mock
	private IPLocationController ipLocationController;

	@InjectMocks
	private GeoLocationAPIServiceImpl geoLocationAPIService;

	@BeforeEach
	public void setUp() {
		geoLocationAPIService = mock(GeoLocationAPIServiceImpl.class);
		ipLocationController = new IPLocationController(geoLocationAPIService);
	}

	@Test
	public void testGetGeoIPLocation_Success() throws Exception {
		GeoAPIRequest request = new GeoAPIRequest("USername", "A@ds12as", "127.0.0.1");
		GeoLocationAPICustomResponse response = new GeoLocationAPICustomResponse(UUID.randomUUID(), "City", "Country");

		when(geoLocationAPIService.getGeoIPLocationDetails(request)).thenReturn(response);

		ResponseEntity<?> expectedResponse = ResponseEntity.ok(response);
		ResponseEntity<?> actualResponse = ipLocationController.getGeoIPLocation(request);

		assertEquals(expectedResponse, actualResponse);
		verify(geoLocationAPIService, times(1)).getGeoIPLocationDetails(request);
	}

}
