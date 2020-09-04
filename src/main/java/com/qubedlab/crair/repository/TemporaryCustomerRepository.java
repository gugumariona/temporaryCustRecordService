package com.qubedlab.crair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qubedlab.crair.models.TemporaryCustomer;



public interface TemporaryCustomerRepository extends JpaRepository<TemporaryCustomer, String>{
	
	@Query("FROM TemporaryCustomer WHERE licenseIDNumber = ?1")
	public List<TemporaryCustomer> listCustomersByDLNumber(String dlNumber);

}
