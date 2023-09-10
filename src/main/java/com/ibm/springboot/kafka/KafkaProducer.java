package com.ibm.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.Message;

@Service
public class KafkaProducer {

	@Autowired
	private StreamBridge streamBridge;

	public void sendMessage(Message message) {
		streamBridge.send("producer-out-0", message);
	}
}
