package com.qubedlab.crair.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerIntegrationIDs;
import com.qubedlab.crair.repository.CustomerIntegrationIDsRepository;
import com.qubedlab.crair.service.CustomerIntegrationIDsService;

@Service
public class CustomerIntegrationIDsServiceImpl implements CustomerIntegrationIDsService {

    @Autowired
    private CustomerIntegrationIDsRepository repo;

    @Override
    public List<CustomerIntegrationIDs> listCustomersByGlobalID(String customerGlobalID) {
	// TODO Auto-generated method stub
	return repo.listCustomersByGlobalID(customerGlobalID);
    }

    @Override
    public CustomerIntegrationIDs saveCustomerIntegrationIDs(CustomerIntegrationIDs customerIntegrationIDs) {
	// TODO Auto-generated method stub
	return repo.save(customerIntegrationIDs);
    }

}
