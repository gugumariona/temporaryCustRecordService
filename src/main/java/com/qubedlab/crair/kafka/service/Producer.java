package com.qubedlab.crair.kafka.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qubedlab.crair.util.Constants;



@Service
public class Producer {

private static final Logger logger = LoggerFactory.getLogger(Producer.class);
private static final String TOPIC = "cust-complete-request";

@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

@Autowired
private Gson gson;


public void send(Map<String, Object> data) {
	
//	logger.info("#### -> Producing message -> %s", data);
	System.out.println("######  send producer " + data.toString());
	;
	 this.kafkaTemplate.send(TOPIC, gson.toJson(data));

	 
}


public void sendToGeneralResponse(Map<String, Object> data) {
	

	 this.kafkaTemplate.send("gen-response", gson.toJson(data));

	 
}

public void sendToIDVerificationRequest(Map<String, Object> data) {
	

	 this.kafkaTemplate.send("id-verification-request", gson.toJson(data));

	 
}

}

