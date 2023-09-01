package com.ibm.springboot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ibm.springboot.dto.GeoAPIRequest;
import com.ibm.springboot.dto.GeoLocationAPIResponse;

@FeignClient(name = "${app.geoip.config.service}", url = "${app.geoip.config.url}")
public interface GeoLocationAPIClient {

	@GetMapping("/{ip}")
	public GeoLocationAPIResponse getIPDetailsByIp(@PathVariable("ip") String ip);
}
