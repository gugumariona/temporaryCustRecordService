package com.qubedlab.crair.processorImpl;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.processor.CustomerDetailsProcessor;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import com.qubedlab.crair.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsProcessorImpl implements CustomerDetailsProcessor {

    @Autowired
    CustomerPersonalDetailsService cpdService;

    @Override
    public Response<CustomerPersonalDetails> addCustomerPVehicle(String globalCustomerId, String vin) {
        if (globalCustomerId == null ) {
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Invalid customer ID");
        }
        if (vin == null ){
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Invalid skill ID");
        }
        return cpdService.addCustomerPVehicle(globalCustomerId, vin);

    }

    @Override
    public Response<CustomerPersonalDetails> addCustomerTradeVehicle(String globalCustomerId, String vin) {
        if (globalCustomerId == null ) {
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Invalid customer ID");
        }
        if (vin == null ){
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Invalid skill ID");
        }
        return cpdService.addCustomerTradeVehicle(globalCustomerId,vin);
    }
}
