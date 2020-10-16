/*
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
@Table(name="purchase_vehicle")
public class PurchaseVehicles {


    @Id
    @Column(name = "purchase_veh_vin", nullable = false,length = 255)
    private String VIN;

    @OneToOne
    @JoinColumn(name="customerGlobalID")
    private CustomerPersonalDetails customerPersonalDetails;



    @Column(name = "purchase_veh_stock", nullable = false,length = 255)
    private String StockType;


    @Column(name = "purchase_veh_year", nullable = false,length = 255)
    private int Year;

   @Column(name = "purchase_veh_make", nullable = false,length = 255)
    private String MakeName;

   @Column(name = "purchase_veh_model", nullable = false,length = 255)
    private String Model;


    @Column(name = "purchase_veh_trim", nullable = false,length = 255)
    private String TrimLevel;

   @Column(name = "purchase_veh_salePrice", nullable = false,length = 255)
    private double StickerPrice;

    @Column(name = "purchase_veh_mileage", nullable = false,length = 255)
    private double Mileage;

}**/
