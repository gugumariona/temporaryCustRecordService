package com.qubedlab.crair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerPreviousContactDetails;
import com.qubedlab.crair.repository.CustomerPreviousContactDetailsRepository;
import com.qubedlab.crair.service.CustomerPreviousContactDetailsService;

@Service
public class CustomerPreviousContactDetailsImpl implements CustomerPreviousContactDetailsService {

    @Autowired
    private CustomerPreviousContactDetailsRepository repo;

    @Override
    public CustomerPreviousContactDetails getCustomerPreviousContactDetails(String globalID) {

	if (repo.findById(globalID).isPresent()) {
	    return repo.findById(globalID).get();
	}
	return new CustomerPreviousContactDetails();
    }

    @Override
    public CustomerPreviousContactDetails saveCustomerPreviousContactDetails(
	    CustomerPreviousContactDetails customerPreviousContactDetails) {

	return repo.save(customerPreviousContactDetails);
    }

}
