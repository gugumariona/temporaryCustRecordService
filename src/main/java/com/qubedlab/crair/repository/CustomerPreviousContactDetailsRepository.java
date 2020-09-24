package com.qubedlab.crair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qubedlab.crair.models.CustomerPreviousContactDetails;

public interface CustomerPreviousContactDetailsRepository
	extends JpaRepository<CustomerPreviousContactDetails, String> {

}
