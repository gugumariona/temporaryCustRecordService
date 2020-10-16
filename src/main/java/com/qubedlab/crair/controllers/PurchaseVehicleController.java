package com.qubedlab.crair.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.models.PVehicles;

import com.qubedlab.crair.processor.CustomerDetailsProcessor;
import com.qubedlab.crair.repository.CustomerPersonalDetailsRepository;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import com.qubedlab.crair.service.PVehiclesService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import static org.springframework.http.HttpStatus.OK;
import javax.validation.Valid;
import java.util.List;
import com.qubedlab.crair.service.Response;


@Slf4j
@RestController
@RequestMapping("/inventory")
public class PurchaseVehicleController {

    @Autowired
    CustomerPersonalDetailsRepository customerPersonalDetailsRepository;
   @Autowired
    CustomerPersonalDetailsService customerPersonalDetailsService;
   @Autowired
   PVehiclesService pVehiclesService;

   @Autowired
   CustomerDetailsProcessor customerDetailsProcessor;
 WebClient  clientInvent = WebClient.builder().baseUrl("https://qubedlab-dealer-inventory.herokuapp.com/extract")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

    @RequestMapping(value = "/getPurchaseVehicles", method = RequestMethod.POST)
    @ApiOperation(notes = "Endpoint to get CDK inventory information",
            value = "Gets Purchase Vehicles from CDK", nickname = "getPurchaseVehicles",
            tags = {"PurchaseVehicles"})
    public @ResponseBody String myActionInventoryExtract(
            @RequestParam(value = "queryId", required = false) String queryId,
            @RequestParam(value = "deltaDate", required = false) String deltaDate,
            @RequestParam(value = "qparamInvCompany", required = false) String qparamInvCompany,
            @RequestParam(value = "dealerId", required = false) String dealerId) throws JsonProcessingException, JsonProcessingException {
        String myRes = clientInvent.post()
                .uri(uriBuilder -> uriBuilder.path("/inventory").queryParam("dealerId", dealerId)
                        .queryParam("deltaDate", deltaDate).queryParam("queryId", queryId)
                        .queryParam("qparamInvCompany", qparamInvCompany).build())
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
                .retrieve().bodyToMono(String.class).block();
        log.info("Post sent successfully " + myRes);


        JSONObject purchases = new JSONObject(myRes);
        JSONObject getSth = purchases.getJSONObject("InventoryVehicleExtract").getJSONObject("InventoryVehicle");
        String message = getSth.getString("OptionDescriptions");
        PVehicles  pv = new PVehicles();
        pv.setVIN(getSth.getString("VIN"));
        pv.setYear(getSth.getInt("Year"));
        pv.setStickerPrice(getSth.getDouble("StickerPrice"));
        pv.setStockType(getSth.getString("StockType"));
        pv.setModel(getSth.getString("Model"));
        pv.setTrimLevel(getSth.getString("TrimLevel"));
        pv.setMakeName(getSth.getString("MakeName"));
        pv.setMileage(getSth.getDouble("Mileage"));
        pVehiclesService.save(pv);

        log.info("message"+pv.toString());
        return  myRes;

    }


    @GetMapping("/pVehicles/{vin}")
    @ApiOperation(notes = "Endpoint to get a specific purchase vehicle using an vin number",
            value = "Gets Purchase Vehicles by Vin Number", nickname = "getPurchaseByVin",
            tags = {"PurchaseVehicles"})
    public PVehicles getAll(@PathVariable(value = "vin") String vin) {

        return pVehiclesService.getVehByVin(vin) ;
    }


//get all
    @RequestMapping(value="/pVehicles",method = RequestMethod.GET)
    @ApiOperation(notes = "Endpoint to get all purchase vehicles",
            value = "Gets all Purchase Vehicles", nickname = "getAllPurchaseVehicles",
            tags = {"PurchaseVehicles"})
    public ResponseEntity<List<PVehicles>> getAllPurchaseVehicles() {
        return new ResponseEntity<>(pVehiclesService.findAll(), HttpStatus.OK);
    }


         //add purchase vehicle to customer


    @PostMapping(value = {"pvehicles/{globalCustomerId}/{vin}"})
    @ApiOperation(notes = "Endpoint to get add a purchase vehicle to a customer",
            value = "Add purchase vehicle to customer", nickname = "addToCustomer",
            tags = {"PurchaseVehicles"})
    public ResponseEntity<Response<CustomerPersonalDetails>> addCustomerPurchaseVehicle(
            @PathVariable("globalCustomerId") final String globalCustomerId,
            @PathVariable("vin") final String vin ) {
  return new ResponseEntity<>(customerDetailsProcessor.addCustomerPVehicle(globalCustomerId, vin),OK);
    }


}
