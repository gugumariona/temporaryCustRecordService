package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customer_contact_details")
public class CustomerContactDetails {

    @Id
    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "unit_or_appt")
    private String unitOrAppt;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "county")
    private String county;

    @Column(name = "msa")
    private String msa;

    @Column(name = "msa_code")
    private String msaCode;

    @Column(name = "res_type")
    private String resType;

    @Column(name = "rent_own")
    private String rentOwn;

    @Column(name = "time_at_present_address_yrs")
    private String timeAtPresentAddressYrs;

    @Column(name = "time_at_present_address_mnths")
    private String timeAtPresentAddressMnths;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "email_preffered")
    private String emailPreffered;

    @Column(name = "email_primary")
    private String emailPrimary;

    @Column(name = "email_secondary")
    private String Email_secondary;

    @Column(name = "email_work")
    private String emailWork;

    @Column(name = "mailing_street_address1")
    private String mailingStreetAddress1;

    @Column(name = "mailing_street_address2")
    private String mailingStreetAddress2;

    @Column(name = "mailing_city")
    private String mailingCity;

    @Column(name = "mailing_state")
    private String mailingState;

    @Column(name = "mailing_postal_code")
    private String mailingPostalCode;

    @Column(name = "mailing_msa")
    private String mailingMSA;

    @Column(name = "mailing_msa_code")
    private String mailingMSACode;

    @Column(name = "mailing_Jurisdiction_code")
    private String mailingJurisdictionCode;

}
