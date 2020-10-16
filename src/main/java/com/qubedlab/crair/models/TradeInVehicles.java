package com.qubedlab.crair.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor

@AllArgsConstructor
@Table(name="trade_vehicle")
public class TradeInVehicles {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "trade_veh_vin", nullable = false,length = 255)
    private String trade_veh_vin;

    @Column(name = "trade_veh_stock", nullable = false,length = 255)
    private String trade_veh_stock;


    @Column(name = "trade_veh_year", nullable = false,length = 255)
    private int trade_veh_year;

    @Column(name = "trade_veh_make", nullable = false,length = 255)
    private String purchase_veh_make;

    @Column(name = "trade_veh_model", nullable = false,length = 255)
    private String trade_veh_model;


    @Column(name = "trade_veh_trim", nullable = false,length = 255)
    private String trade_veh_trim;

    @Column(name = "trade_veh_salePrice", nullable = false,length = 255)
    private double trade_veh_salePrice;

    @Column(name = "trade_veh_mileage", nullable = false,length = 255)
    private double trade_veh_mileage;





}
