package com.qubedlab.crair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qubedlab.crair.models.CustomerPreviousContactDetails;
import org.springframework.stereotype.Repository;

@Repository("customerPreviousContactDetailsRepository")
public interface CustomerPreviousContactDetailsRepository
	extends JpaRepository<CustomerPreviousContactDetails, String> {

}
