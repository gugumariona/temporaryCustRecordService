package com.qubedlab.crair.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.repository.CustomerPersonalDetailsRepository;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;

@Service
public class CustomerPersonalDetailsServiceImpl implements CustomerPersonalDetailsService {

    @Autowired
    private CustomerPersonalDetailsRepository temporaryCustomerRepository;

    @Override
    public List<CustomerPersonalDetails> listCustomersByDLNumber(String dlNumber) {

	return temporaryCustomerRepository.listCustomersByDLNumber(dlNumber);
    }

    @Override
    public CustomerPersonalDetails saveCustomer(CustomerPersonalDetails customer) {

	return temporaryCustomerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String dlNumber) {
	temporaryCustomerRepository.deleteById(dlNumber);

    }

    @Override
    public void deleteCustomer(CustomerPersonalDetails customer) {
	temporaryCustomerRepository.delete(customer);

    }

    @Override
    public List<CustomerPersonalDetails> listCustomers() {

	return temporaryCustomerRepository.findAll();
    }

    @Override
    public List<CustomerPersonalDetails> listCustomerByGlobalID(String customerGlobalID) {

	return temporaryCustomerRepository.listCustomerByGlobalID(customerGlobalID);
    }

    @Override
    public CustomerPersonalDetails CustomerByGlobalID(String customerGlobalID) {

	return temporaryCustomerRepository.CustomerByGlobalID(customerGlobalID);
    }

    @Override
    public List<CustomerPersonalDetails> serchCustomer(String dlNumber) {
	// TODO Auto-generated method stub
	return temporaryCustomerRepository.serchCustomer(dlNumber);
    }

}
