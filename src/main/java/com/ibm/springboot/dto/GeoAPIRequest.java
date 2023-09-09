package com.ibm.springboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GeoAPIRequest {

	private String username;

	private String password;

	private String IP;

}
