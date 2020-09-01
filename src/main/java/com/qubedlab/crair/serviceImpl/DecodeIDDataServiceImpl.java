package com.qubedlab.crair.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.aspectj.weaver.reflect.Java14GenericSignatureInformationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.PDF417AbbreviationsDescriptionsService;

@Service
public class DecodeIDDataServiceImpl  implements DecodeIDDataService{
	
	@Autowired
	private PDF417AbbreviationsDescriptionsService pdf417Service ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DecodeIDDataServiceImpl.class);
	

	@Override
	public Map<String, Object> decodeCustomerScannedId(String idData) {
    
		Map<String,Object> customerData = new HashMap<String,Object>();
	
		 
		 List<String> splitIdDataList = Arrays.asList(idData.split(":"));
			Map<String,String> pdf417Map=pdf417Service.getAllPDF417Codes();
			for(String idString : splitIdDataList) {
				if(pdf417Map.containsKey(idString.trim().substring(0, 3))) {
					 try {
						 customerData.put(pdf417Map.get(idString.trim().substring(0, 3)), idString.trim().substring(3, idString.length())) ;
					
					 }catch(StringIndexOutOfBoundsException siobe) {
						 System.out.println("### " + idString);
						 LOGGER.error(siobe.getStackTrace().toString());
						 siobe.printStackTrace();
					 }
				}else {
					
					if(idString.contains("DAQ")) {
						
						
						 customerData.put(pdf417Map.get("DAQ"), idString.substring(idString.indexOf("DAQ") + 3)) ;
						
						
					}
					
				}
				 
				
			}
			
		if(ValidateLicenseExpirationDate(formatDates(customerData))>-1) {
			
			return	formatDates(customerData) ;
		}else {
			
			Map<String ,Object> data  = new HashMap<String ,Object>();
			Map<String ,Object> data2  = new HashMap<String ,Object>();
			data.put("status", "License Expired");
			data.put("customerData" , customerData);
			
			
			
			data2.put("error", data) ;
			return data2 ;
		}	
		 
	}
	
	
private Map<String, Object> formatDates(Map<String, Object> decodedIDData){
	//08152020
	SimpleDateFormat scannedDobFormatte=new SimpleDateFormat("MMddyyyy");
    
    try {
	
		
		final String OLD_FORMAT = "MMddyyyy";
		final String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

		
		Date Date_of_Birth = sdf.parse(decodedIDData.get("Date_of_Birth").toString());
		Date License_Expiration_Date = sdf.parse(decodedIDData.get("License_Expiration_Date").toString());
		sdf.applyPattern(NEW_FORMAT);
		
		
		decodedIDData.put("Date_of_Birth", sdf.format(Date_of_Birth)) ;
		decodedIDData.put("License_Expiration_Date", sdf.format(License_Expiration_Date)) ;
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
   // new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(PaidDate.trim());
    ValidateLicenseExpirationDate(decodedIDData);
	return decodedIDData;}

private int ValidateLicenseExpirationDate(Map<String, Object> decodedIDData){

    int response =-1 ;
    try {
	
		final String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
		Date License_Expiration_Date = sdf.parse(decodedIDData.get("License_Expiration_Date").toString());

		LocalDate License_Expiration_Date_l = License_Expiration_Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		System.out.println( " ######### today " + LocalDate.now());
		System.out.println( " ######### License_Expiration_Date " + License_Expiration_Date);
		System.out.println( " ######### compare tiem " + License_Expiration_Date_l.compareTo(LocalDate.now()));
//		LocalDate endDate_l = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		response = License_Expiration_Date_l.compareTo(LocalDate.now()) ;
		
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	         
	return response;
	}

}
