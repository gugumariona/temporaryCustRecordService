package com.qubedlab.crair.service;

import java.util.List;

import com.qubedlab.crair.models.CustomerPersonalDetails;

public interface CustomerPersonalDetailsService {

    public List<CustomerPersonalDetails> listCustomersByDLNumber(String dlNumber);

    public List<CustomerPersonalDetails> listCustomers();

    public CustomerPersonalDetails saveCustomer(CustomerPersonalDetails customer);

    public void deleteCustomer(String dlNumber);

    void deleteCustomer(CustomerPersonalDetails customer);

    public List<CustomerPersonalDetails> listCustomerByGlobalID(String customerGlobalID);

    public CustomerPersonalDetails CustomerByGlobalID(String customerGlobalID);

    public List<CustomerPersonalDetails> serchCustomer(String dlNumber);

}
