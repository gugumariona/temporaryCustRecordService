package com.qubedlab.crair.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubedlab.crair.kafka.service.Producer;
import com.qubedlab.crair.models.TemporaryCustomer;
import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.TemporaryCustomerService;



@RestController
@RequestMapping("/scan")
@CrossOrigin
public class ScanIdController {

	@Autowired
	private DecodeIDDataService decodeIDDataService;
	
	@Autowired
	private TemporaryCustomerService temporaryCustomerService;
	
	@Autowired
    Producer producer;
	
	@PostMapping(path = "/customerid")
	public Map<String, Object> saveIdData(@RequestBody Map<String, Object> data) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		String idData = data.get("idData").toString() ;
		map.put("customerData", decodeIDDataService.decodeCustomerScannedId(idData));
		return map ;
		
	}
		

	@GetMapping(path = "/customers")
	public List<TemporaryCustomer> customers() {
		return temporaryCustomerService.listCustomers();
		
	}
	
	
	
	@GetMapping(path = "/customer/dlnumber/{dlNumber}")
	public TemporaryCustomer customersByDLNumber(@PathVariable("dlNumber") String dlNumber) {
		List<TemporaryCustomer> temporaryCustomerList = temporaryCustomerService.listCustomersByDLNumber(dlNumber) ;
		
		if(temporaryCustomerList.size()>0) {
			return temporaryCustomerService.listCustomersByDLNumber(dlNumber).get(0) ;
			
		}else {
			
			return null  ;
		}

		
	}
	
	
	@PostMapping(path = "/customerid/kafka")
	public Map<String, Object> sendIdDataToKafka(@RequestBody Map<String, Object> data) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("customerData",data);
		producer.send(data);
		return map ;
		
	}
}
