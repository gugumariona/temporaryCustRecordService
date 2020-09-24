package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customer_previous_contact_details")
public class CustomerPreviousContactDetails {

    @Id
    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "Prev_Street_Number")
    private String Prev_Street_Number;

    @Column(name = "Prev_Street_Name")
    private String Prev_Street_Name;

    @Column(name = "Prev_Unit_Or_Appt")
    private String Prev_Unit_Or_Appt;

    @Column(name = "Prev_City")
    private String Prev_City;

    @Column(name = "Prev_State")
    private String Prev_State;

    @Column(name = "Prev_Postal_Code")
    private String Prev_Postal_Code;

    @Column(name = "Prev_County")
    private String Prev_County;

    @Column(name = "Prev_MSA")
    private String Prev_MSA;

    @Column(name = "Prev_MSA_Code")
    private String Prev_MSA_Code;

    @Column(name = "Prev_Res_Type")
    private String Prev_Res_Type;

    @Column(name = "Prev_Res_Rent_Own")
    private String Prev_Res_Rent_Own;

    @Column(name = "Time_at_Prev_Res_Mths")
    private String Time_at_Prev_Res_Mths;

    @Column(name = "Time_at_Prev_Res_Yrs")
    private String Time_at_Prev_Res_Yrs;

    @Column(name = "Prev_2_Street_Number")
    private String Prev_2_Street_Number;

    @Column(name = "Prev_2_Street_Name")
    private String Prev_2_Street_Name;

    @Column(name = "Prev_2_Unit_Or_Appt")
    private String Prev_2_Unit_Or_Appt;

    @Column(name = "Prev_2_City")
    private String Prev_2_City;

    @Column(name = "Prev_2_State")
    private String Prev_2_State;

    @Column(name = "Prev_2_Postal_Code")
    private String Prev_2_Postal_Code;

    @Column(name = "Prev_2_County")
    private String Prev_2_County;

    @Column(name = "Prev_2_MSA")
    private String Prev_2_MSA;

    @Column(name = "Prev_2_MSA_Code")
    private String Prev_2_MSA_Code;

    @Column(name = "Prev_2_Res_Type")
    private String Prev_2_Res_Type;

    @Column(name = "Prev_2_Res_Rent_Own")
    private String Prev_2_Res_Rent_Own;

    @Column(name = "Time_at_Prev2_Res_Mths")
    private String Time_at_Prev2_Res_Mths;

    @Column(name = "Time_at_Prev2_Res_Yrs")
    private String Time_at_Prev2_Res_Yrs;

    @Column(name = "Prev_3_Street_Number")
    private String Prev_3_Street_Number;

    @Column(name = "Prev_3_Street_Name")
    private String Prev_3_Street_Name;

    @Column(name = "Prev_3_Unit_Or_Appt")
    private String Prev_3_Unit_Or_Appt;

    @Column(name = "Prev_3_City")
    private String Prev_3_City;

    @Column(name = "Prev_3_State")
    private String Prev_3_State;

    @Column(name = "Prev_3_Postal_Code")
    private String Prev_3_Postal_Code;

    @Column(name = "Prev_3_County")
    private String Prev_3_County;

    @Column(name = "Prev_3_MSA")
    private String Prev_3_MSA;

    @Column(name = "Prev_3_MSA_Code")
    private String Prev_3_MSA_Code;

    @Column(name = "Prev_3_Res_Type")
    private String Prev_3_Res_Type;

    @Column(name = "Prev_3_Res_Rent_Own")
    private String prev3ResRentOwn;

    @Column(name = "time_at_prev3_res_mths")
    private String timeAtPrev3ResMths;

    @Column(name = "time_at_prev3_res_yrs")
    private String timeAtPrev3ResYrs;

}
