package com.ibm.springboot.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.springboot.client.GeoLocationAPIClient;
import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.dto.GeoLocationAPIResponse;
import com.ibm.springboot.exception.InvalidCountryException;
import com.ibm.springboot.service.GeoLocationAPIService;
import com.ibm.springboot.validator.ValidationService;

@Service
public class GeoLocationAPIServiceImpl implements GeoLocationAPIService {
	private Logger logger = LoggerFactory.getLogger(GeoLocationAPIServiceImpl.class);
	private final GeoLocationAPIClient client;
	private final ValidationService validateService;

	@Value("${app.geoLocation.countryCode}")
	private String countryCode;

	@Value("${app.geoLocation.failResponseStatus}")
	private String failResponseStatus;

	public GeoLocationAPIServiceImpl(GeoLocationAPIClient client, ValidationService validateService) {
		this.client = client;
		this.validateService = validateService;
	}

	@Override
	public GeoLocationAPICustomResponse getGeoIPLocationDetails(GeoAPIRequest request) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Calling validate method of ValidateService : {} from getIPDetails()", request);
		validateService.validate(request);

		GeoLocationAPIResponse response = client.getIPDetailsByIp(request.getIP());

		handleInvalidResponse(response);

		handleInvalidIpCountry(response);

		return new GeoLocationAPICustomResponse(UUID.randomUUID(), response.getQuery(), response.getCity());
	}

	private void handleInvalidIpCountry(GeoLocationAPIResponse response) {
		// TODO Auto-generated method stub
		if (response.getCountryCode() == null || !response.getCountryCode().equals(countryCode)) {
			throw new InvalidCountryException("IP address does not belong to canada");
		}
	}

	private void handleInvalidResponse(GeoLocationAPIResponse response) {
		// TODO Auto-generated method stub
		if (response.getStatus() == null || response.getStatus().equals(failResponseStatus)) {
			throw new InvalidCountryException(response.getMessage());
		}
	}

}
