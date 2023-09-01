package com.ibm.springboot.dto;

import java.util.UUID;

public class GeoLocationAPICustomResponse{
	private UUID uuid;
	private String userName;
	private String city;
	
	public GeoLocationAPICustomResponse() {
	}

	public GeoLocationAPICustomResponse(UUID uuid, String userName, String city) {
		this.uuid = uuid;
		this.userName = userName;
		this.city = city;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
