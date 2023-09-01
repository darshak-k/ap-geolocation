package com.ibm.springboot.dto;

public class GeoAPIRequest {
	private String username;
	private String password;
	private String IP;

	public GeoAPIRequest(String username, String password, String iP) {
		this.username = username;
		this.password = password;
		IP = iP;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

}
