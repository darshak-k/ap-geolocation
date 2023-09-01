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
import com.ibm.springboot.service.GeoLocationAPIService;

@Service
public class GeoLocationAPIServiceImpl implements GeoLocationAPIService {
	private Logger logger = LoggerFactory.getLogger(GeoLocationAPIServiceImpl.class);
	private final GeoLocationAPIClient client;

	@Value("${app.geoLocation.countryCode}")
	private String countryCode;

	@Value("${app.geoLocation.failResponseStatus}")
	private String failResponseStatus;

	public GeoLocationAPIServiceImpl(GeoLocationAPIClient client) {
		this.client = client;
	}

	@Override
	public GeoLocationAPICustomResponse getGeoIPLocationDetails(GeoAPIRequest request) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Calling validate method of ValidateService : {} from getIPDetails()", request);
		GeoLocationAPIResponse response = client.getIPDetailsByIp(request.getIP());

		return new GeoLocationAPICustomResponse(UUID.randomUUID(), response.getQuery(), response.getCity());
	}

}
