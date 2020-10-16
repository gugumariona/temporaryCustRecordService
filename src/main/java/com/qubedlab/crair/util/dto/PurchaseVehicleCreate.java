package com.qubedlab.crair.util.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@JsonDeserialize
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PurchaseVehicleCreate {


    private String purchase_veh_vin;

    private String purchase_veh_stock;


    private int purchase_veh_year;
    private String purchase_veh_make;
    private String purchase_veh_model;


    private String purchase_veh_trim;

    private double purchase_veh_salePrice;

    private double purchase_veh_mileage;

}
