package com.qubedlab.crair.service;

import java.util.List;

import com.qubedlab.crair.models.CustomerPersonalDetails;

public interface CustomerPersonalDetailsService {

    public List<CustomerPersonalDetails> listCustomersByDLNumber(String dlNumber);

    public List<CustomerPersonalDetails> listCustomers();

    public CustomerPersonalDetails saveCustomer(CustomerPersonalDetails customer);

    public void deleteCustomer(String dlNumber);


    void deleteCustomer(CustomerPersonalDetails customer);

    List<CustomerPersonalDetails> listCustomerByGlobalID(String customerGlobalID);

    CustomerPersonalDetails CustomerByGlobalID(String customerGlobalID);

    List<CustomerPersonalDetails> serchCustomer(String dlNumber);

    Response<CustomerPersonalDetails> findByGlobal(String customerGlobalID);

    Response<CustomerPersonalDetails> addCustomerPVehicle(String customerGlobalID, String VIN);

    Response<CustomerPersonalDetails> addCustomerTradeVehicle(String customerGlobalID, String VIN);


}
