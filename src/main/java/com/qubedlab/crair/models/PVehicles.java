package com.qubedlab.crair.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity


@JsonIgnoreProperties(ignoreUnknown = true)
public class PVehicles {


    private String StockType;

    private int Year;

    private String MakeName;

    private String Model;
    @Id

    private String VIN;

     private String TrimLevel;


    private double StickerPrice;


    private double Mileage;
    @Column(name = "StockNo", columnDefinition = "int default 0",nullable = false)
     private int StockNo;

    private String Color;

    @Column(name = "Selected", columnDefinition = "boolean default false", nullable = false)
      private boolean Selected =false;


    public PVehicles() {
    }


    public int getStockNo() {
        return StockNo;
    }

    public void setStockNo(int stockNo) {
        StockNo = stockNo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
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

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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

    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        this.Selected = selected;
    }

    public List<CustomerPersonalDetails> getCustomerPersonalDetails() {
        return customerPersonalDetails;
    }

    public void setCustomerPersonalDetails(List<CustomerPersonalDetails> customerPersonalDetails) {
        this.customerPersonalDetails = customerPersonalDetails;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "purchases", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<CustomerPersonalDetails> customerPersonalDetails = new ArrayList<>();




}
