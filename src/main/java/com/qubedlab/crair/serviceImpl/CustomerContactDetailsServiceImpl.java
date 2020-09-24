package com.qubedlab.crair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerContactDetails;
import com.qubedlab.crair.repository.CustomerContactDetailsRepository;
import com.qubedlab.crair.service.CustomerContactDetailsService;

@Service
public class CustomerContactDetailsServiceImpl implements CustomerContactDetailsService {

    @Autowired
    private CustomerContactDetailsRepository repo;

    @Override
    public CustomerContactDetails getCustomerContactDetails(String customerGlobalId) {
	if (repo.findById(customerGlobalId).isPresent()) {
	    return repo.findById(customerGlobalId).get();
	}

	return new CustomerContactDetails();
    }

    @Override
    public CustomerContactDetails saveCustomerContactDetails(CustomerContactDetails customerContactDetails) {

	return repo.save(customerContactDetails);
    }

}
