package com.qubedlab.crair.service;

import java.util.List;

import com.qubedlab.crair.models.CustomerIntegrationIDs;

public interface CustomerIntegrationIDsService {

    public List<CustomerIntegrationIDs> listCustomersByGlobalID(String customerGlobalID);

    public CustomerIntegrationIDs saveCustomerIntegrationIDs(CustomerIntegrationIDs customerIntegrationIDs);
}
