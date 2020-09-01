package com.qubedlab.crair.kafka.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.serviceImpl.DecodeIDDataServiceImpl;




@Service
public class Consumer
{
	@Autowired
	private DecodeIDDataService decodeIDDataService;
	
	@Autowired
	private Producer producer;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
	
	
    @KafkaListener(topics = "cust-complete-request", groupId = "group_id")
    public void consume(String customerprofile) {
    	
    	JsonObject convertedObject = new Gson().fromJson(customerprofile, JsonObject.class);
    	JsonObject  customerData =convertedObject.getAsJsonObject("customerData");
    	
    	String Mailing_City  =  customerData.get("Mailing_City").getAsString() ;
    	System.out.println("######### Message convertedObject -> %s " + convertedObject.get("Mailing_City").getAsString());
        
    }
    
    
    
    @KafkaListener(topics = "create-profile", groupId = "group_id")
    public void consumeCreateProfile(String CreateProfile) {
    	
    	
    	LOGGER.info("CreateProfile :" + CreateProfile);
    	JsonObject convertedObject = new Gson().fromJson(CreateProfile, JsonObject.class);
    	Map<String, Object> responseDataMap  =decodeIDDataService.decodeCustomerScannedId(convertedObject.get("idData").toString());
    	if(responseDataMap.containsKey("error")) {
    		System.out.println("######### Message error " + responseDataMap);
    		producer.sendToGeneralResponse(responseDataMap);
    		
    		
    	} else {
    		
    		producer.sendToGeneralResponse(responseDataMap);
    		producer.sendToIDVerificationRequest(responseDataMap);
    	}
    	
        
    }

	
}

