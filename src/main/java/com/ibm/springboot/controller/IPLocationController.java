package com.ibm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.service.GeoLocationAPIService;

@RestController
@RequestMapping("/api/")
public class IPLocationController {
	private Logger logger = LoggerFactory.getLogger(IPLocationController.class);

	private final GeoLocationAPIService geoLocationAPIService;

	public IPLocationController(GeoLocationAPIService geoLocationAPIService) {
		this.geoLocationAPIService = geoLocationAPIService;
	}

	@PostMapping("/search-geolocation-ip")
	public ResponseEntity<?> getGeoIPLocation(@RequestBody GeoAPIRequest request)
			throws Exception {
		logger.debug("Calling getGeoIPLocationDetails() method of geoLocationAPIService : {} from getGeoIPLocation()",
				request);
		return ResponseEntity.ok(geoLocationAPIService.getGeoIPLocationDetails(request));
	}
	
	
}
