package com.qubedlab.crair.processor;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import org.springframework.stereotype.Service;
import com.qubedlab.crair.service.Response;

@Service
public interface CustomerDetailsProcessor {
    Response<CustomerPersonalDetails> addCustomerPVehicle(String globalCustomerId,String vin);
    Response<CustomerPersonalDetails> addCustomerTradeVehicle(String globalCustomerId,String vin);

}
