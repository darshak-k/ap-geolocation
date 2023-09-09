package com.ibm.springboot.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeoLocationAPICustomResponse {
	private UUID uuid;
	private String userName;
	private String city;
}
