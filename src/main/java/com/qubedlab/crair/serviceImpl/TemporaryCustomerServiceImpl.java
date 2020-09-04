package com.qubedlab.crair.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.TemporaryCustomer;
import com.qubedlab.crair.repository.TemporaryCustomerRepository;
import com.qubedlab.crair.service.TemporaryCustomerService;

@Service
public class TemporaryCustomerServiceImpl  implements TemporaryCustomerService {

	@Autowired
	private TemporaryCustomerRepository temporaryCustomerRepository ;
	
	@Override
	public List<TemporaryCustomer> listCustomersByDLNumber(String dlNumber) {
	
		return temporaryCustomerRepository.listCustomersByDLNumber(dlNumber);
	}

	@Override
	public TemporaryCustomer saveCustomer(TemporaryCustomer customer) {
		
		return temporaryCustomerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(String dlNumber) {
		temporaryCustomerRepository.deleteById(dlNumber);
		
	}

	@Override
	public void deleteCustomer(TemporaryCustomer customer) {
		temporaryCustomerRepository.delete(customer);
		
	}

	@Override
	public List<TemporaryCustomer> listCustomers() {
		
		return temporaryCustomerRepository.findAll();
	}

}
