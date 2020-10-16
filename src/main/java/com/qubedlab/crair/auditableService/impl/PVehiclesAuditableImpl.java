package com.qubedlab.crair.auditableService.impl;

import com.qubedlab.crair.auditableService.GenericAuditableService;
import com.qubedlab.crair.models.PVehicles;
import com.qubedlab.crair.repository.PVehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PVehiclesAuditableImpl implements GenericAuditableService {

    @Autowired
    PVehiclesRepository repository;
    @Override
    public PVehicles findById(String id) throws NullPointerException {
        return repository.findById(id).orElse(null);
    }
}
