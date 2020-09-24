package com.qubedlab.crair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qubedlab.crair.models.CustomerContactDetails;

public interface CustomerContactDetailsRepository extends JpaRepository<CustomerContactDetails, String> {

}
