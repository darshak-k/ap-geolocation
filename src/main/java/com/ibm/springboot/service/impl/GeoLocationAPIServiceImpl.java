package com.ibm.springboot.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.service.GeoLocationAPIService;

@Service
public class GeoLocationAPIServiceImpl implements GeoLocationAPIService {
	private Logger logger = LoggerFactory.getLogger(GeoLocationAPIServiceImpl.class);

	@Value("${app.geoLocation.countryCode}")
	private String countryCode;

	@Value("${app.geoLocation.failResponseStatus}")
	private String failResponseStatus;

	@Override
	public GeoLocationAPICustomResponse getGeoIPLocationDetails(GeoAPIRequest request) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Calling validate method of ValidateService : {} from getIPDetails()", request);

		return new GeoLocationAPICustomResponse();
	}

}
