package com.qubedlab.crair.kafka.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qubedlab.crair.models.CustomerBiometricDetails;
import com.qubedlab.crair.models.CustomerContactDetails;
import com.qubedlab.crair.models.CustomerParentBranch;
import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.service.CustomerBiometricDetailsService;
import com.qubedlab.crair.service.CustomerContactDetailsService;
import com.qubedlab.crair.service.CustomerIntegrationIDsService;
import com.qubedlab.crair.service.CustomerParentBranchService;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import com.qubedlab.crair.service.CustomerPreviousContactDetailsService;
import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.GlobalIDService;
import com.qubedlab.crair.service.RelativesContactDetailsService;

@Service
public class Consumer {
    @Autowired
    private DecodeIDDataService decodeIDDataService;

    @Autowired
    private Producer producer;

    @Autowired
    private CustomerParentBranchService customerParentBranchService;

    @Autowired
    private CustomerPersonalDetailsService temporaryCustomerService;

    @Autowired
    private GlobalIDService globalIDService;

    @Autowired
    private CustomerBiometricDetailsService CustomerBiometricDetailsService;

    @Autowired
    private CustomerIntegrationIDsService customerIntegrationIDsService;

    @Autowired
    private CustomerContactDetailsService customerContactDetailsService;

    @Autowired
    private CustomerPreviousContactDetailsService customerPreviousContactDetailsService;

    @Autowired
    private RelativesContactDetailsService RelativesContactDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "cust-complete-request", groupId = "group_id")
    public void consume(String customerprofile) {

	JsonObject convertedObject = new Gson().fromJson(customerprofile, JsonObject.class);
	JsonObject customerData = convertedObject.getAsJsonObject("customerData");

	String Mailing_City = customerData.get("Mailing_City").getAsString();
	System.out.println(
		"######### Message convertedObject -> %s " + convertedObject.get("Mailing_City").getAsString());

    }

    @KafkaListener(topics = "create-profile", groupId = "group_id")
    public void consumeCreateProfile(String CreateProfile) {

	try {

	    LOGGER.info("CreateProfile :" + CreateProfile);
	    JsonObject convertedObject = new Gson().fromJson(CreateProfile, JsonObject.class);
	    Map<String, Object> responseDataMap = decodeIDDataService
		    .decodeCustomerScannedId(convertedObject.get("idData").toString());

	    saveCustomerScannedID(responseDataMap);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    CustomerPersonalDetails temporaryCustomerWithErrorStatus(Map<String, Object> responseDataMap,
	    String customerGlobalID) {
	CustomerPersonalDetails tc = new CustomerPersonalDetails();
	tc.setDateOfBirth(responseDataMap.get("Date_of_Birth").toString());

	tc.setFirstName(responseDataMap.get("First_Name").toString());

	tc.setLastName(responseDataMap.get("Last_Name").toString());
	tc.setLicenseExpirationDate(responseDataMap.get("License_Expiration_Date").toString());
	tc.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());

	tc.setMiddleInitial(responseDataMap.get("Eye_Color").toString());
	tc.setMiddleName(responseDataMap.get("Middle_Name").toString());

	tc.setDateCreated(LocalDate.now());

	tc.setCustomerGlobalID(customerGlobalID.toString());
	tc.setStatus("Blocked");
	tc.setStatusDescription(responseDataMap.get("errorDescription").toString());

	return tc;
    }

    CustomerBiometricDetails customerBiometricDetails(Map<String, Object> responseDataMap, String customerGlobalID) {
	CustomerBiometricDetails cbd = new CustomerBiometricDetails();
	cbd.setEyeColor(responseDataMap.get("Eye_Color").toString());
	cbd.setHairColor(responseDataMap.get("Hair_Color").toString());
	cbd.setHeight_in_FT_IN(responseDataMap.get("Height_in_FT_IN").toString());
	cbd.setSex(responseDataMap.get("Sex").toString());
	cbd.setCustomerGlobalID(customerGlobalID);
	return cbd;
    }

    CustomerContactDetails customerContactDetails(Map<String, Object> responseDataMap, String customerGlobalID) {

	CustomerContactDetails ccd = new CustomerContactDetails();
	ccd.setMailingJurisdictionCode(responseDataMap.get("Mailing_Jurisdiction_Code").toString());
	ccd.setMailingPostalCode(responseDataMap.get("Mailing_Postal_Code").toString());

	ccd.setMailingCity(responseDataMap.get("Mailing_City").toString());
	ccd.setCounty(responseDataMap.get("Country territory of issuance").toString());
	ccd.setMailingStreetAddress1(responseDataMap.get("Mailing_Street_Address1").toString());
	ccd.setCustomerGlobalID(customerGlobalID);
	return ccd;
    }

    CustomerPersonalDetails temporaryCustomer(Map<String, Object> responseDataMap, String customerGlobalID) {
	CustomerPersonalDetails tc = new CustomerPersonalDetails();
	tc.setDateOfBirth(responseDataMap.get("Date_of_Birth").toString());

	tc.setFirstName(responseDataMap.get("First_Name").toString());

	tc.setLastName(responseDataMap.get("Last_Name").toString());
	tc.setLicenseExpirationDate(responseDataMap.get("License_Expiration_Date").toString());
	tc.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());

	tc.setMiddleInitial(responseDataMap.get("Eye_Color").toString());
	tc.setMiddleName(responseDataMap.get("Middle_Name").toString());

	tc.setCustomerGlobalID(customerGlobalID.toString());

	tc.setStatus("Active");

	tc.setDateCreated(LocalDate.now());

	return tc;
    }

    Map<String, Object> saveCustomerScannedID(Map<String, Object> responseDataMap) {

	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> data = new LinkedHashMap<>();
	Map<String, Object> dataParent = new LinkedHashMap<>();

	String customerGlobalID = globalIDService.globalIDGenerator(responseDataMap);
	if (responseDataMap.containsKey("error")) {// if there is an error eg blocked , license expired etc

	    if (temporaryCustomerService.listCustomerByGlobalID(customerGlobalID.toString()).size() == 0) {

		temporaryCustomerService
			.saveCustomer(temporaryCustomerWithErrorStatus(responseDataMap, customerGlobalID));

		customerContactDetailsService
			.saveCustomerContactDetails(customerContactDetails(responseDataMap, customerGlobalID));

		CustomerBiometricDetailsService
			.saveCustomerBiometricDetails(customerBiometricDetails(responseDataMap, customerGlobalID));

		CustomerParentBranch cpb = new CustomerParentBranch();
		cpb.setCustomerGlobalID(customerGlobalID.toString());
		cpb.setBranchId("1509931");
		cpb.setParentId("1509932");
		cpb.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());
		cpb.setDateLastScanned(LocalDateTime.now());
		customerParentBranchService.save(cpb);
	    }
	    producer.sendToGeneralResponse(responseDataMap);
	    data.put("customerPersonalDetails", temporaryCustomerWithErrorStatus(responseDataMap, customerGlobalID));
	    data.put("customerBiometricDetails", customerBiometricDetails(responseDataMap, customerGlobalID));
	    data.put("customerContactDetails", customerContactDetails(responseDataMap, customerGlobalID));
	    dataParent.put("CustomerData", data);
	    return dataParent;

	} else {// not blocked

	    if (temporaryCustomerService.listCustomerByGlobalID(customerGlobalID.toString()).size() == 0) { // if not

		temporaryCustomerService.saveCustomer(temporaryCustomer(responseDataMap, customerGlobalID));

		customerContactDetailsService
			.saveCustomerContactDetails(customerContactDetails(responseDataMap, customerGlobalID));

		CustomerBiometricDetailsService
			.saveCustomerBiometricDetails(customerBiometricDetails(responseDataMap, customerGlobalID));

		CustomerParentBranch cpb = new CustomerParentBranch();
		cpb.setCustomerGlobalID(customerGlobalID.toString());
		cpb.setBranchId("1509931");
		cpb.setParentId("1509932");
		cpb.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());
		cpb.setDateLastScanned(LocalDateTime.now());
		customerParentBranchService.save(cpb);

	    } else { // if not blocked and there is an existing record then update

		List<CustomerParentBranch> cpbList = customerParentBranchService
			.listCustomersByParentBranchlicenseIDNumber("1509932", "1509931",
				responseDataMap.get("License_ID_Number").toString());
		if (cpbList.size() > 0) {
		    CustomerParentBranch cpbToUpdate = cpbList.get(0);
		    cpbToUpdate.setDateLastScanned(LocalDateTime.now());
		    customerParentBranchService.save(cpbToUpdate);
		} else { // if not blocked and there no existing record then insert

		    CustomerParentBranch cpb = new CustomerParentBranch();
		    cpb.setCustomerGlobalID(customerGlobalID.toString());
		    cpb.setBranchId("1509931");
		    cpb.setParentId("1509932");
		    cpb.setLicenseIDNumber(responseDataMap.get("License_ID_Number").toString());
		    cpb.setDateLastScanned(LocalDateTime.now());
		    customerParentBranchService.save(cpb);
		}

	    }
	    producer.sendToGeneralResponse(responseDataMap);
	    producer.sendToIDVerificationRequest(responseDataMap);

	    data.put("customerPersonalDetails", temporaryCustomerWithErrorStatus(responseDataMap, customerGlobalID));
	    data.put("customerBiometricDetails", customerBiometricDetails(responseDataMap, customerGlobalID));
	    data.put("customerContactDetails", customerContactDetails(responseDataMap, customerGlobalID));
	    dataParent.put("CustomerData", data);

	    return dataParent;
	}

    }
}
