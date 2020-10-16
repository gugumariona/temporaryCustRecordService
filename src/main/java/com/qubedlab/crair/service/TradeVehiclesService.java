package com.qubedlab.crair.service;



import com.qubedlab.crair.models.TradeVehicles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeVehiclesService {



    public List<TradeVehicles>findAll() ;

    public TradeVehicles getVehByVin(String vin);

    public TradeVehicles save(TradeVehicles tradeVehicles);
    Response<TradeVehicles> findById(String vin);
}
