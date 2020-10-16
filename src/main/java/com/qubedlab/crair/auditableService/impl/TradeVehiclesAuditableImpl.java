package com.qubedlab.crair.auditableService.impl;

import com.qubedlab.crair.auditableService.api.TradeVehiclesAuditable;
import com.qubedlab.crair.models.TradeVehicles;
import com.qubedlab.crair.repository.TradeVehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeVehiclesAuditableImpl implements TradeVehiclesAuditable {

    @Autowired
    TradeVehiclesRepository tradeVehiclesRepository;


    @Override
    public TradeVehicles findById(String id) throws Exception {
        return tradeVehiclesRepository.findById(id).orElse(null);
    }
}
