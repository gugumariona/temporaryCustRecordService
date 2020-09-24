package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customer_biometric_details")
public class CustomerBiometricDetails {

    @Id
    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "sex")
    private String sex;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "hair_color")
    private String hairColor;

    @Column(name = "height_in_cm")
    private String heightInCM;

    @Column(name = "height_in_ft_in")
    private String height_in_FT_IN;

    @Column(name = "weight_in_KG")
    private String weight_in_KG;

    @Column(name = "weight_in_LBS")
    private String weight_in_LBS;

}
