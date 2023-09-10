package com.ibm.springboot.kafka;

import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.ibm.springboot.dto.Message;

@Service
public class KafkaConsumer implements Consumer<Message> {

	@Override
	public void accept(Message message) {
		// TODO Auto-generated method stub
		System.out.println(message.toString());
	}
}
