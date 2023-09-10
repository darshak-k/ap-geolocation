package com.ibm.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
public class GeoAPIRequest {

	private String username;

	private String password;

	private String IP;

}
