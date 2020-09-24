package com.qubedlab.crair.service;

import com.qubedlab.crair.models.CustomerBiometricDetails;

public interface CustomerBiometricDetailsService {

    public CustomerBiometricDetails getCustomerBiometricDetails(String globalID);

    public CustomerBiometricDetails saveCustomerBiometricDetails(CustomerBiometricDetails customerBiometricDetails);
}
