package com.qubedlab.crair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qubedlab.crair.models.CustomerIntegrationIDs;

public interface CustomerIntegrationIDsRepository extends JpaRepository<CustomerIntegrationIDs, Long> {

    @Query("FROM CustomerIntegrationIDs WHERE customerGlobalID = ?1")
    public List<CustomerIntegrationIDs> listCustomersByGlobalID(String customerGlobalID);
}
