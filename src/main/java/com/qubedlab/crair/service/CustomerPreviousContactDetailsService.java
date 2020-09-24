package com.qubedlab.crair.service;

import com.qubedlab.crair.models.CustomerPreviousContactDetails;

public interface CustomerPreviousContactDetailsService {

    public CustomerPreviousContactDetails getCustomerPreviousContactDetails(String globalID);

    public CustomerPreviousContactDetails saveCustomerPreviousContactDetails(
	    CustomerPreviousContactDetails customerPreviousContactDetails);

}
