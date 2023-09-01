package com.ibm.springboot.service;

import com.ibm.springboot.dto.GeoLocationAPICustomResponse;
import com.ibm.springboot.dto.GeoAPIRequest;

public interface GeoLocationAPIService {

	public GeoLocationAPICustomResponse getGeoIPLocationDetails(GeoAPIRequest request) throws Exception;
}
