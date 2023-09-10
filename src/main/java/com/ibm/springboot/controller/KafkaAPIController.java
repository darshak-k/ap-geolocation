package com.ibm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.springboot.dto.Message;
import com.ibm.springboot.kafka.KafkaProducer;

@RestController
@RequestMapping("api/kafka")
public class KafkaAPIController {
	private Logger logger = LoggerFactory.getLogger(KafkaAPIController.class);
	private KafkaProducer kafkaProducer;

	public KafkaAPIController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@PostMapping("/")
	public ResponseEntity<Void> produceKafkaMessage(@RequestBody Message message) throws Exception {
		logger.info("Calling produceKafkaMessage() method of KafkaAPIController : {} from getGeoIPLocation()", message);
		kafkaProducer.sendMessage(message);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
