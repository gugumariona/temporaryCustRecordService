package com.qubedlab.crair.controllerTest;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.models.PVehicles;
import com.qubedlab.crair.repository.PVehiclesRepository;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import com.qubedlab.crair.service.PVehiclesService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
public class InventoryControllerTest {

    @Autowired
   private PVehiclesService purchaseVehicleService;
    @Autowired
    private CustomerPersonalDetailsService cdpService;
    @MockBean
    private PVehiclesRepository purchaseVehiclesRepository;

    @Test
   public void getAllPurchaseVehicles() throws Exception {
       List<PVehicles> purchaseVehicles = new ArrayList<>();
       purchaseVehicles.add(new PVehicles("ael5228",2020,"toyota","bmw","m7","spoiler",2000.00,0.00));
        purchaseVehicles.add(new PVehicles("acg7264",2020,"gd60","toyota","m7","spoiler",2000.00,0.00));
       when(purchaseVehiclesRepository.findAll()).thenReturn(purchaseVehicles);
        assertEquals(2,purchaseVehicleService.findAll().size());
  }

    //test for getting by vin number

    @Test

    public void getByVin(){

        String vin ="ael5228";

       when(purchaseVehiclesRepository.findByVin(vin)).thenReturn(new PVehicles("ael5228",2020,"toyota","bmw","m7","spoiler",2000.00,0.00));
        assertEquals("bmw",purchaseVehicleService.getVehByVin(vin).getModel());
    }

    //test to add a vehicle to a user

   void addPVehicleInformation(){
        CustomerPersonalDetails cpd = new CustomerPersonalDetails();
        


   }

    @Test
    //String to json conversion
             void stringToJson() {

        String json = "{ \"make\": \"toyota\", \"year\": 2020 }";
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);


        Assert.assertTrue(jsonObject.isJsonObject());
        Assert.assertTrue(jsonObject.get("make").getAsString().equals("toyota"));
        Assert.assertTrue(jsonObject.get("year").getAsString().equals("2020"));



             }

}


