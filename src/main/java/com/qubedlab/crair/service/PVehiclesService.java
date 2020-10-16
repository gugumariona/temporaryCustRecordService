package com.qubedlab.crair.service;

import com.qubedlab.crair.models.PVehicles;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PVehiclesService {

    public PVehicles save(PVehicles pVehicles);
    public PVehicles getVehByVin(String vin);

    public List<PVehicles>findAll();
    Response<PVehicles> findById(String vin);
}
