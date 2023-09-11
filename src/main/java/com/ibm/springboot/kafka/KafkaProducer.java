package com.ibm.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.User;

@Service
public class KafkaProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private StreamBridge streamBridge;

	@Value("${spring.kafka.topic-json.name}")
	private String kafkaTemplateTopicName;

	private KafkaTemplate<String, User> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessageUsingStreamBridge(User user) {
		streamBridge.send("producer-out-0", user);
	}

	public void sendMessageUsingKafkaTemplate(User data) {
		System.out.println(kafkaTemplateTopicName);
		LOGGER.info(String.format("Message sent -> %s", data.toString()));
		kafkaTemplate.send(kafkaTemplateTopicName, data);
	}

}
