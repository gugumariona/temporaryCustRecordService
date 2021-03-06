package com.qubedlab.crair.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Data
@Table(name = "customer_personal_details")
public class CustomerPersonalDetails {

    @Id
    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "License_ID_Number")
    private String licenseIDNumber;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "License_Expiration_Date")
    private String licenseExpirationDate;

    @Column(name = "Date_of_Birth")
    private String dateOfBirth;

    @Column(name = "Social_Security_Number_Fraud_2")
    private String socialSecurityNumberFraud2;

    @Column(name = "Social_Security_Number_Fraud_3")
    private String socialSecurityNumberFraud3;

    @Column(name = "Social_Security_Number_Fraud_1")
    private String socialSecurityNumberFraud1;
    @Column(name = "Social_Security_Number")
    private String socialSecurityNumber;

    @Column(name = "Non_Resident_Indicator")
    private String nonResidentIndicator;

    @Column(name = "Name_Prefix")
    private String namePrefix;

    @Column(name = "Name_Suffix")
    private String nameSuffix;

    @Column(name = "Given_Name")
    private String givenName;

    @Column(name = "Family_Name")
    private String familyName;

    @Column(name = "Middle_Initial")
    private String middleInitial;

    @Column(name = "Middle_Name")
    private String middleName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "status")
    private String status;

    @Column(name = "status_description")
    private String statusDescription;

    @Column(name = "License_State")
    private String licenseState;

    @Column(name = "Privacy_Indicator")
    private String privacyIndicator;

    @Column(name = "Privacy_Type")
    private String privacyType;

    @Column(name = "Educ_Level")
    private String educLevel;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "customer_purchase_vehicles",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"customer_global_id", "VIN"})},
            joinColumns = {
                    @JoinColumn(name = "customer_global_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "vin")
            }
    )

    private List<PVehicles> purchases = new ArrayList<>();



    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "customer_trade_vehicles",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"customer_global_id", "VIN"})},
            joinColumns = {
                    @JoinColumn(name = "customer_global_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "vin")
            }
    )


    List <TradeVehicles>trades = new ArrayList<>();





    //trade relationship



}
