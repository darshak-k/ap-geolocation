package com.ibm.springboot.kafka;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.User;

@Service
public class KafkaConsumer implements Consumer<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@Override
	public void accept(User message) {
		// TODO Auto-generated method stub
		LOGGER.info("Received accept method KafkaConsumer: {}", message.toString());
	}

	@KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(User user) {
		LOGGER.info(String.format("Json message recieved -> %s", user.toString()));
	}
}
