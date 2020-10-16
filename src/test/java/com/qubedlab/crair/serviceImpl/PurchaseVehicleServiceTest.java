package com.qubedlab.crair.serviceImpl;


import com.qubedlab.crair.repository.PVehiclesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class PurchaseVehicleServiceTest {

    @MockBean
    PVehiclesRepository purchaseVehiclesRepository;


    @Test
    void getAllPurchaseVehiclesService(){

       // PurchaseVehicles purchaseVehicles = new PurchaseVehicles("ael5228","test",2020,"bmw","m7","spoiler",2000.00,0.00);



    }

}
