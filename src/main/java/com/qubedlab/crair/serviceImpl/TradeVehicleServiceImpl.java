package com.qubedlab.crair.serviceImpl;

import com.qubedlab.crair.auditableService.impl.TradeVehiclesAuditableImpl;
import com.qubedlab.crair.models.PVehicles;
import com.qubedlab.crair.models.TradeInVehicles;
import com.qubedlab.crair.models.TradeVehicles;
import com.qubedlab.crair.repository.TradeVehiclesRepository;
import com.qubedlab.crair.service.Response;
import com.qubedlab.crair.service.TradeVehiclesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TradeVehicleServiceImpl implements TradeVehiclesService {

    @Autowired
    TradeVehiclesRepository tradeVehiclesRepository;
    @Autowired
    TradeVehiclesAuditableImpl tradeVehiclesImpl;

    public List<TradeVehicles> findAll() {

        List<TradeVehicles> tradeVehicles = tradeVehiclesRepository.findAll();
        return tradeVehicles;
    }

    public TradeVehicles getVehByVin(String vin) {

        return tradeVehiclesRepository.findByVin(vin);
    }

    @Override
    public TradeVehicles save(TradeVehicles tradeVehicles) {
        return tradeVehiclesRepository.save(tradeVehicles);
    }

    @Override
    public Response<TradeVehicles> findById(String vin) {

        try {
            TradeVehicles tradeVehicles = tradeVehiclesImpl.findById(vin);
            if (tradeVehicles == null) {
                return new Response<TradeVehicles>().buildErrorResponse("tradeVehicles with supplied ID not found");
            }
            return new Response<TradeVehicles>().buildSuccessResponse(tradeVehicles);
        } catch (NullPointerException ex) {
            log.error(ex.getMessage());
            return new Response<TradeVehicles>().buildErrorResponse(ex.getMessage());
        }
    }
}
