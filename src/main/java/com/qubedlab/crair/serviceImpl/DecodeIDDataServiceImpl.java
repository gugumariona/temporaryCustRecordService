package com.qubedlab.crair.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.PDF417AbbreviationsDescriptionsService;

@Service
public class DecodeIDDataServiceImpl implements DecodeIDDataService {

    @Autowired
    private PDF417AbbreviationsDescriptionsService pdf417Service;

    private static final Logger LOGGER = LoggerFactory.getLogger(DecodeIDDataServiceImpl.class);

    @Override
    public Map<String, Object> decodeCustomerScannedId(String idData) {

	Map<String, Object> customerData = new HashMap<String, Object>();

	List<String> splitIdDataList = Arrays.asList(idData.split(":"));

	Map<String, String> pdf417Map = pdf417Service.getAllPDF417Codes();
	for (String idString : splitIdDataList) {
	    try {
		String idStringTrimmed = idString.trim();

		if (pdf417Map.containsKey(idStringTrimmed.trim().substring(0, 3))) {

		    customerData.put(pdf417Map.get(idStringTrimmed.trim().substring(0, 3)),
			    idStringTrimmed.trim().substring(3, idStringTrimmed.length()));

		} else {

		    if (idStringTrimmed.contains("DAQ")) {

			customerData.put(pdf417Map.get("DAQ"),
				idStringTrimmed.substring(idStringTrimmed.indexOf("DAQ") + 3));

		    }

		}

	    } catch (StringIndexOutOfBoundsException siobe) {
		System.out.println("### " + idString);
		LOGGER.error(siobe.getStackTrace().toString());
		siobe.printStackTrace();
	    }
	}

	if (ValidateLicenseExpirationDate(formatDates(customerData)) > -1) {
	    customerData.put("Mailing_Postal_Code", customerData.get("Mailing_Postal_Code").toString().substring(0, 5));
	    return (customerData);
	} else {

	    Map<String, Object> data = new HashMap<String, Object>();
	    Map<String, Object> data2 = new HashMap<String, Object>();
	    customerData.put("error", "error");
	    customerData.put("errorDescription",
		    "License Expired on " + customerData.get("License_Expiration_Date").toString());
	    customerData.put("Mailing_Postal_Code", customerData.get("Mailing_Postal_Code").toString().substring(0, 5));
	    data.put("customerData", customerData);

	    data2.put("error", data);
	    return customerData;
	}

    }

    private Map<String, Object> formatDates(Map<String, Object> decodedIDData) {

	SimpleDateFormat scannedDobFormatte = new SimpleDateFormat("MMddyyyy");

	try {

	    final String OLD_FORMAT = "MMddyyyy";
	    final String NEW_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

	    Date Date_of_Birth = sdf.parse(decodedIDData.get("Date_of_Birth").toString());
	    Date License_Expiration_Date = sdf.parse(decodedIDData.get("License_Expiration_Date").toString());
	    sdf.applyPattern(NEW_FORMAT);

	    decodedIDData.put("Date_of_Birth", new SimpleDateFormat(NEW_FORMAT).format(Date_of_Birth));
	    decodedIDData.put("License_Expiration_Date",
		    new SimpleDateFormat(NEW_FORMAT).format(License_Expiration_Date));

	} catch (ParseException e) {
	    LOGGER.error(e.getMessage());
	    e.printStackTrace();
	}

	ValidateLicenseExpirationDate(decodedIDData);
	return decodedIDData;
    }

    private int ValidateLicenseExpirationDate(Map<String, Object> decodedIDData) {

	int response = -1;
	try {

	    final String NEW_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
	    Date License_Expiration_Date = sdf.parse(decodedIDData.get("License_Expiration_Date").toString());

	    LocalDate License_Expiration_Date_l = License_Expiration_Date.toInstant().atZone(ZoneId.systemDefault())
		    .toLocalDate();

	    LOGGER.info(" ######### today " + LocalDate.now());
	    LOGGER.info(" ######### License_Expiration_Date " + License_Expiration_Date);
	    LOGGER.info(" ######### compare tiem " + License_Expiration_Date_l.compareTo(LocalDate.now()));

	    response = License_Expiration_Date_l.compareTo(LocalDate.now());

	} catch (ParseException e) {

	    e.printStackTrace();
	}

	return response;
    }

}
