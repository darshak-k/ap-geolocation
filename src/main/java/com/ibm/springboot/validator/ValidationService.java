package com.ibm.springboot.validator;

import com.ibm.springboot.dto.GeoAPIRequest;

public interface ValidationService {
	public void validate(GeoAPIRequest request) throws Exception;
}
