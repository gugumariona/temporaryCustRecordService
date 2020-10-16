package com.qubedlab.crair.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DecodeIDDataService {

	public Map<String, Object> decodeCustomerScannedId (String idData) ;
}
