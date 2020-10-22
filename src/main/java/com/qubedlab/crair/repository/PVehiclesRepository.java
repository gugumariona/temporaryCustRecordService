package com.qubedlab.crair.repository;

import com.qubedlab.crair.models.PVehicles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PVehiclesRepository extends JpaRepository<PVehicles,String> {

    @Query("FROM PVehicles WHERE VIN = ?1")
    public PVehicles findByVin(String vin);



}
