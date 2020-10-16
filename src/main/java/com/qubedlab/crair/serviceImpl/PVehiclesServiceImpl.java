package com.qubedlab.crair.serviceImpl;

import com.qubedlab.crair.auditableService.impl.PVehiclesAuditableImpl;
import com.qubedlab.crair.models.PVehicles;

import com.qubedlab.crair.repository.PVehiclesRepository;
import com.qubedlab.crair.service.PVehiclesService;
import com.qubedlab.crair.service.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PVehiclesServiceImpl implements PVehiclesService {
    @Autowired
    PVehiclesRepository pVehiclesRepository;

    @Autowired
    PVehiclesAuditableImpl pVehiclesService;
    @Override
    public PVehicles save(PVehicles pVehicles) {
        return pVehiclesRepository.save(pVehicles);
    }

    @Override
    public PVehicles getVehByVin(String vin) {

        return pVehiclesRepository.findByVin(vin);
    }

    public List<PVehicles> findAll() {

        List<PVehicles> purchaseVehicles = pVehiclesRepository.findAll();
        return purchaseVehicles;
    }

    @Override
    public Response<PVehicles> findById(String vin) {
        try {
           PVehicles pVehicles = pVehiclesService.findById(vin);
            if (pVehicles == null) {
                return new Response<PVehicles>().buildErrorResponse("pVehicles with supplied ID not found");
            }
            return new Response<PVehicles>().buildSuccessResponse(pVehicles);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new Response<PVehicles>().buildErrorResponse(ex.getMessage());
        }
    }
    }

