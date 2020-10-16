package com.qubedlab.crair.controllers;


import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.models.TradeVehicles;
import com.qubedlab.crair.processor.CustomerDetailsProcessor;
import com.qubedlab.crair.service.Response;
import com.qubedlab.crair.service.TradeVehiclesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class TradeInVehicleController {

    @Autowired
    TradeVehiclesService tradeVehiclesService;
    @Autowired
    CustomerDetailsProcessor customerDetailsProcessor;

//get trade vehicle by vin number

    @GetMapping("/tradeVehicles/{vin}")
    @ApiOperation(notes = "Endpoint to get a specific trade vehicle using an vin number",
            value = "Gets Trade Vehicles by Vin Number", nickname = "getTradeByVin",
            tags = {"TradeVehicles"})
    public TradeVehicles getByVin(@PathVariable(value = "vin") String vin) {
        return tradeVehiclesService.getVehByVin(vin);
    }


    //get all
    @GetMapping("/tradeVehicles/")
    @ApiOperation(notes = "Endpoint to get all trade vehicles",
            value = "Gets all Trade Vehicles ", nickname = "getAllTrade",
            tags = {"TradeVehicles"})
    public ResponseEntity<List<TradeVehicles>> getAllPurchaseVehicles() {
        return new ResponseEntity<>(tradeVehiclesService.findAll(), HttpStatus.OK);
    }


    //add trade vehicle to customer
    @PostMapping(value = {"tradeVehicle/{globalCustomerId}/{vin}"})
    @ApiOperation(notes = "Endpoint to add a specific trade vehicle to a customer",
            value = "Adds Trade Vehicles to a customer", nickname = "addTradeVehicle",
            tags = {"TradeVehicles"})
    public ResponseEntity<Response<CustomerPersonalDetails>> addCustomerTradeVehicle(
            @PathVariable("globalCustomerId") final String globalCustomerId,
            @PathVariable("vin") final String vin ) {
        return new ResponseEntity<>(customerDetailsProcessor.addCustomerTradeVehicle(globalCustomerId, vin),OK);
    }




}
