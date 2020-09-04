package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "temporary_customer")
public class TemporaryCustomer {

	@Id
	@Column(name = "License_ID_Number")
	private String licenseIDNumber;
	
	
	@Column(name = "First_Name")
	private String firstName;
	
	@Column(name = "Mailing_MSA_Code")
	private String mailingMSACode;

	@Column(name = "Residence_Street_Address1")
	private String residenceStreetAddress1;
	
	@Column(name = "Residence_Street_Address2")
	private String residenceStreetAddress2;
	
	@Column(name = "Residence_City")
	private String residenceCity;
	
	@Column(name = "Residence_State")
	private String residenceState;
	
	@Column(name = "Residence_County")
	private String residenceCounty;
	
	@Column(name = "Residence_Jurisdiction_Code")
	private String residenceJurisdictionCode;
	
	@Column(name = "Residence_MSA")
	private String residenceMSA;
	
	
	@Column(name = "Residence_MSA_Code")
	private String residenceMSACode;
	
	
	@Column(name = "Residence_Postal_Code")
	private String residencePostalCode;
	
	
	@Column(name = "License_Expiration_Date")
	private String licenseExpirationDate;
	
	@Column(name = "Date_of_Birth")
	private String dateOfBirth;
	
	@Column(name = "Sex")
	private String sex;
	
	@Column(name = "Social_Security_Number")
	private String socialSecurityNumber;
	
	
	@Column(name = "Non_Resident_Indicator")
	private String nonResidentIndicator;
	
	@Column(name = "Height_in_FT_IN")
	private String heightInFTIN;
	
	@Column(name = "Height_in_CM")
	private String height_in_CM;
	
	
	@Column(name = "Weight_in_LBS")
	private String weight_in_LBS;
	
	@Column(name = "Weight_in_KG")
	private String weight_in_KG;
	
	@Column(name = "Eye_Color")
	private String eyeColor;
	
	@Column(name = "Hair_Color")
	private String hairColor;

	@Column(name = "Mailing_Street_Address2")
	private String mailingStreetAddress2;
	
	@Column(name = "Mailing_City")
	private String mailingCity;
	
	
	@Column(name = "Mailing_Street_Address1")
	private String mailingStreetAddress1;
	
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
	
	
	@Column(name = "Mailing_MSA")
	private String Mailing_MSA;
	
	@Column(name = "Mailing_Jurisdiction_Code")
	private String Mailing_Jurisdiction_Code;
	
	@Column(name = "Mailing_Postal_Code")
	private String Mailing_Postal_Code;
	
	@Column(name = "Mailing_State")
	private String Mailing_State;
	


}
