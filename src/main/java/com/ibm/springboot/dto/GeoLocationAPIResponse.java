package com.ibm.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeoLocationAPIResponse {
	private String query;
	private String status;
	private String message;
	private String country;
	private String countryCode;
	private String regionName;
	private String city;
	private String zip;
	private double lat;
	private double lon;
	private String timezone;
}
