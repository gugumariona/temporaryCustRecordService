
package com.qubedlab.crair.kafka.service;


import com.google.gson.Gson;

import com.qubedlab.crair.models.TradeVehicles;

import com.qubedlab.crair.service.TradeVehiclesService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;



@Slf4j
@Service
public class TradeInConsumer {

    @Autowired
    TradeVehiclesService tradeVehiclesService;



    @KafkaListener(topics = "trade_vehicles",groupId ="group_id")
    public void consumeTradeVehicles(String tradeVehicles) {
    log.info("VehicleDetails :" + tradeVehicles);

        Gson g = new Gson();
        TradeVehicles tradeData = g.fromJson(tradeVehicles, TradeVehicles.class);


         TradeVehicles tv = new TradeVehicles();
        log.info("This is the engine"+tradeData.getEngine());
        tv.setEngine(tradeData.getEngine());
        tv.setMade_In(tradeData.getMade_In());
        tv.setMake(tradeData.getMake());
        tv.setModel(tradeData.getModel());
        tv.setStyle(tradeData.getStyle());
        tv.setVIN(tradeData.getVIN());

        tv.setYear(tradeData.getYear());
        tradeVehiclesService.save(tv);








    }

}



