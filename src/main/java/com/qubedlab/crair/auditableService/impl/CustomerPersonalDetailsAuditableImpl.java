package com.qubedlab.crair.auditableService.impl;

import com.qubedlab.crair.auditableService.GenericAuditableService;
import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.repository.CustomerPersonalDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerPersonalDetailsAuditableImpl implements GenericAuditableService {

    @Autowired
    CustomerPersonalDetailsRepository repository;
    @Override
    public CustomerPersonalDetails findById(String id) throws Exception {
        return repository.findById(id).orElse(null);
    }
}
