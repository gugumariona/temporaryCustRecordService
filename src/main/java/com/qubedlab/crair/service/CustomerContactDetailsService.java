package com.qubedlab.crair.service;

import com.qubedlab.crair.models.CustomerContactDetails;

public interface CustomerContactDetailsService {

    public CustomerContactDetails getCustomerContactDetails(String customerGlobalId);

    public CustomerContactDetails saveCustomerContactDetails(CustomerContactDetails customerContactDetails);
}
