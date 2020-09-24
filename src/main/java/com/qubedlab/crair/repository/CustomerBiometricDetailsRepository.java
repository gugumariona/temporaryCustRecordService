package com.qubedlab.crair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qubedlab.crair.models.CustomerBiometricDetails;

public interface CustomerBiometricDetailsRepository extends JpaRepository<CustomerBiometricDetails, String> {

}
