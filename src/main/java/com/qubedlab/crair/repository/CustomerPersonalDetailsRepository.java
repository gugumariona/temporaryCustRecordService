package com.qubedlab.crair.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qubedlab.crair.models.CustomerPersonalDetails;

@Transactional
public interface CustomerPersonalDetailsRepository extends JpaRepository<CustomerPersonalDetails, String> {

    @Query("FROM CustomerPersonalDetails WHERE licenseIDNumber = ?1")
    public List<CustomerPersonalDetails> listCustomersByDLNumber(String dlNumber);

    @Query("FROM CustomerPersonalDetails WHERE customerGlobalID = ?1")
    public List<CustomerPersonalDetails> listCustomerByGlobalID(String customerGlobalID);

    @Query("FROM CustomerPersonalDetails WHERE customerGlobalID = ?1")
    public CustomerPersonalDetails CustomerByGlobalID(String customerGlobalID);

    @Query("FROM CustomerPersonalDetails WHERE licenseIDNumber  LIKE %:licenseIDNumber%")
    public List<CustomerPersonalDetails> serchCustomer(String licenseIDNumber);
}
