package com.qubedlab.crair.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PVehicles {

    @JsonProperty("StockType")
    private String StockType;
    @JsonProperty("Year")
    private int Year;
    @JsonProperty("MakeName")
    private String MakeName;
    @JsonProperty("Model")
    private String Model;
    @Id
    @JsonProperty("VIN")
    private String VIN;
    @JsonProperty("TrimLevel")
     private String TrimLevel;
    @JsonProperty("StickerPrice")

    private double StickerPrice;
    @JsonProperty("Mileage")

    private double Mileage;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public PVehicles() {
    }

    public PVehicles(String stockType, int year, String makeName, String model, String VIN, String trimLevel, double stickerPrice, double mileage) {
        StockType = stockType;
        Year = year;
        MakeName = makeName;
        Model = model;
        this.VIN = VIN;
        TrimLevel = trimLevel;
        StickerPrice = stickerPrice;
        Mileage = mileage;
    }

    public String getStockType() {
        return StockType;
    }

    public void setStockType(String stockType) {
        StockType = stockType;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getMakeName() {
        return MakeName;
    }

    public void setMakeName(String makeName) {
        MakeName = makeName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getTrimLevel() {
        return TrimLevel;
    }

    public void setTrimLevel(String trimLevel) {
        TrimLevel = trimLevel;
    }

    public double getStickerPrice() {
        return StickerPrice;
    }

    public void setStickerPrice(double stickerPrice) {
        StickerPrice = stickerPrice;
    }

    public double getMileage() {
        return Mileage;
    }

    public void setMileage(double mileage) {
        Mileage = mileage;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "purchases", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<CustomerPersonalDetails> customerPersonalDetails = new ArrayList<>();




}
