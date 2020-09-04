package com.qubedlab.crair.kafka.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qubedlab.crair.models.TemporaryCustomer;
import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.TemporaryCustomerService;
import com.qubedlab.crair.serviceImpl.DecodeIDDataServiceImpl;




@Service
public class Consumer
{
	@Autowired
	private DecodeIDDataService decodeIDDataService;
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private TemporaryCustomerService temporaryCustomerService;
	
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
    	
    	try {
			
		
    	LOGGER.info("CreateProfile :" + CreateProfile);
    	JsonObject convertedObject = new Gson().fromJson(CreateProfile, JsonObject.class);
    	Map<String, Object> responseDataMap  =decodeIDDataService.decodeCustomerScannedId(convertedObject.get("idData").toString());
    	if(responseDataMap.containsKey("error")) {
    		System.out.println("######### Message error " + responseDataMap);
    		producer.sendToGeneralResponse(responseDataMap);
    		
    		
    	} else {
    		
    		System.out.println(" ####  size "  +temporaryCustomerService.listCustomersByDLNumber(responseDataMap.get("License_ID_Number").toString()).size());;
    		if(temporaryCustomerService.listCustomersByDLNumber(responseDataMap.get("License_ID_Number").toString()).size()==0) {
    			TemporaryCustomer tc  = new TemporaryCustomer();
    			tc.setDateOfBirth(responseDataMap.get("Date_of_Birth").toString());
    			tc.setEyeColor(responseDataMap.get("Eye_Color").toString());
    			tc.setFirstName(responseDataMap.get("First_Name").toString());
    			tc.setHairColor(responseDataMap.get("Hair_Color").toString());
    			
    			tc.setHeightInFTIN(responseDataMap.get("Height_in_FT_IN").toString());
    			tc.setLastName(responseDataMap.get("Last_Name").toString());
    			tc.setLicenseExpirationDate(responseDataMap.get("License_Expiration_Date").toString());
    			tc.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());
    			tc.setMailing_Jurisdiction_Code(responseDataMap.get("Mailing_Jurisdiction_Code").toString());
    			
    			tc.setMailing_Postal_Code(responseDataMap.get("Mailing_Postal_Code").toString());
    			
    			tc.setMailingCity(responseDataMap.get("Mailing_City").toString());
    			
    			tc.setMailingStreetAddress1(responseDataMap.get("Mailing_Street_Address1").toString());
    			
    			tc.setMiddleInitial(responseDataMap.get("Eye_Color").toString());
    			tc.setMiddleName(responseDataMap.get("Middle_Name").toString());
    			
    			tc.setSex(responseDataMap.get("Sex").toString());
    			
    			tc.setResidenceCounty(responseDataMap.get("Country territory of issuance").toString());
    			temporaryCustomerService.saveCustomer(tc) ;
    			
    			
    		}
    		
    		
    		producer.sendToGeneralResponse(responseDataMap);
    		producer.sendToIDVerificationRequest(responseDataMap);
    	}
    	
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
}

