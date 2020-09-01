package com.qubedlab.crair.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubedlab.crair.kafka.service.Producer;
import com.qubedlab.crair.service.DecodeIDDataService;



@RestController
@RequestMapping("/scan")
@CrossOrigin
public class ScanIdController {

	@Autowired
	private DecodeIDDataService decodeIDDataService;
	
	@Autowired
    Producer producer;
	
	@PostMapping(path = "/customerid")
	public Map<String, Object> saveIdData(@RequestBody Map<String, Object> data) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		String idData = data.get("idData").toString() ;
		map.put("customerData", decodeIDDataService.decodeCustomerScannedId(idData));
		return map ;
		
	}
	
	
	@PostMapping(path = "/customerid/kafka")
	public Map<String, Object> sendIdDataToKafka(@RequestBody Map<String, Object> data) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("customerData",data);
		producer.send(data);
		return map ;
		
	}
}
