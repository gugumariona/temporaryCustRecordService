package com.qubedlab.crair.service;

import java.util.List;

import com.qubedlab.crair.models.TemporaryCustomer;

public interface TemporaryCustomerService {

	public List<TemporaryCustomer> listCustomersByDLNumber(String dlNumber);
	
	public List<TemporaryCustomer> listCustomers();
	
	public TemporaryCustomer saveCustomer(TemporaryCustomer customer);

	public void deleteCustomer(String dlNumber);

	void deleteCustomer(TemporaryCustomer customer);
}
