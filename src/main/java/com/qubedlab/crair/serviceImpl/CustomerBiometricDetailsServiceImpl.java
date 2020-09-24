package com.qubedlab.crair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerBiometricDetails;
import com.qubedlab.crair.repository.CustomerBiometricDetailsRepository;
import com.qubedlab.crair.service.CustomerBiometricDetailsService;

@Service
public class CustomerBiometricDetailsServiceImpl implements CustomerBiometricDetailsService {

    @Autowired
    private CustomerBiometricDetailsRepository repo;

    @Override
    public CustomerBiometricDetails getCustomerBiometricDetails(String globalID) {

	if (repo.findById(globalID).isPresent()) {
	    return repo.findById(globalID).get();
	}
	return new CustomerBiometricDetails();
    }

    @Override
    public CustomerBiometricDetails saveCustomerBiometricDetails(CustomerBiometricDetails customerBiometricDetails) {

	return repo.save(customerBiometricDetails);
    }

}
