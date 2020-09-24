package com.qubedlab.crair.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qubedlab.crair.service.GlobalIDService;
import com.qubedlab.crair.util.Constants;

@Service
public class GlobalIDServiceImpl implements GlobalIDService {

    @Override
    public String lastNameReverse(String lastName) {
	String lastNameReverse = "x";
	if (lastName.length() > 2) {

	    String lastThreeLetters = lastName.toLowerCase().substring(lastName.length() - 3, lastName.length());
	    StringBuilder sBuiRev = new StringBuilder(lastThreeLetters);
	    sBuiRev.reverse();

	    lastNameReverse = sBuiRev.toString();

	}
	return lastNameReverse;
    }

    public String monthOfBirth(String date) {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// convert String to LocalDate
	LocalDate localDate = LocalDate.parse(date, formatter);
	localDate.getMonth();
	String month = localDate.getMonth().toString().toLowerCase();

	return month.substring(0, 3);
    }

    public String extractFirstAndLastLettersFirstName(String firstName) {

	if (firstName.length() > 2) {
	    String extractFirstAndLastLettersFirstName = firstName.substring(0, 1)
		    + firstName.substring(firstName.length() - 1, firstName.length());
	    return extractFirstAndLastLettersFirstName.toLowerCase();
	} else {
	    firstName = Constants.firstName;

	    String extractFirstAndLastLettersFirstName = firstName.substring(0, 1)
		    + firstName.substring(firstName.length() - 1, firstName.length());
	    return extractFirstAndLastLettersFirstName.toLowerCase();
	}

    }

    public String dayOfBirth(String date) {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// convert String to LocalDate
	LocalDate localDate = LocalDate.parse(date, formatter);
	int day = localDate.getDayOfMonth();
	if (day < 10) {

	    return "0" + day;
	} else {
	    return day + "";
	}

    }

    public String gender(String gender) {

	if (gender.compareTo("2") == 0) {

	    return "f";
	} else {
	    return "m";
	}

    }

    public String yearOfBirth(String date) {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// convert String to LocalDate
	LocalDate localDate = LocalDate.parse(date, formatter);
	localDate.getMonth();
	int year = localDate.getYear();
	String yearString = String.valueOf(year);
	StringBuilder yearStringBuilder = new StringBuilder(yearString);
	yearStringBuilder.reverse();

	return yearStringBuilder.toString();
    }

    public String driversLicenseNumberExtract(String driversLicenseNumber) {

	return driversLicenseNumber.substring(0, 5);
    }

    String customerHeight(String height) {

	return height.substring(0, 3);
    }

    @Override
    public String globalIDGenerator(Map<String, Object> data) {

	String globalID = lastNameReverse(data.get(Constants.LastName).toString())
		+ data.get(Constants.Mailing_Postal_Code).toString().trim()
		+ monthOfBirth(data.get(Constants.Date_of_Birth).toString().trim())
		+ extractFirstAndLastLettersFirstName(data.get(Constants.First_Name).toString().trim())
		+ dayOfBirth(data.get(Constants.Date_of_Birth).toString().trim())
		+ data.get(Constants.Mailing_Jurisdiction_Code).toString().trim()
		+ gender(data.get(Constants.Sex).toString().trim())
		+ yearOfBirth(data.get(Constants.Date_of_Birth).toString().trim())
		+ driversLicenseNumberExtract(data.get(Constants.License_ID_Number).toString().trim())
		+ customerHeight(data.get(Constants.Height_in_FT_IN).toString().trim())
		+ data.get(Constants.Eye_Color).toString().trim();
	System.out.println("##### " + globalID.toLowerCase().trim());
	return globalID.toLowerCase().trim();
    }

}
