package com.qubedlab.crair.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qubedlab.crair.models.PDF417AbbreviationsDescriptions;
import com.qubedlab.crair.repository.PDF417AbbreviationsDescriptionsRepository;
import com.qubedlab.crair.service.PDF417AbbreviationsDescriptionsService;

@Service
public class PDF417AbbreviationsDescriptionsServiceImpl implements PDF417AbbreviationsDescriptionsService {

	@Autowired
	private PDF417AbbreviationsDescriptionsRepository repo ; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PDF417AbbreviationsDescriptionsServiceImpl.class);
	
	@Override
	public Map<String, String> getAllPDF417Codes() {
		
		Map<String, String>  pdf417CodeMap = new HashMap<String, String>();
		
		repo.findAll().forEach((PDF417AbbreviationsDescriptions pad) -> {
			pdf417CodeMap.put(pad.getAbbreviation(), pad.getDescription());
		}); 
		
		return pdf417CodeMap;
	}

}
