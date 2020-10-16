package com.qubedlab.crair.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class TradeVehicles {


    private String Engine;
    private String Made_In;
    private String Make;
    private String Model;
    private String Style;
    private String Type;
    @Id
    private String VIN;
    private String Year;


    public TradeVehicles(String engine, String made_In, String make, String model, String style, String type, String VIN, String year) {
        Engine = engine;
        Made_In = made_In;
        Make = make;
        Model = model;
        Style = style;
        Type = type;
        this.VIN = VIN;
        Year = year;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public TradeVehicles() {
    }



    public String getEngine() {
        return Engine;
    }

    public void setEngine(String engine) {
        Engine = engine;
    }

    public String getMade_In() {
        return Made_In;
    }

    public void setMade_In(String made_In) {
        Made_In = made_In;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "trades", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<CustomerPersonalDetails> customerPersonalTrade = new ArrayList<>();




}
