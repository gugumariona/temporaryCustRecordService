package com.qubedlab.crair.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubedlab.crair.kafka.service.Producer;
import com.qubedlab.crair.models.CustomerArchiveConfig;
import com.qubedlab.crair.models.CustomerBiometricDetails;
import com.qubedlab.crair.models.CustomerContactDetails;
import com.qubedlab.crair.models.CustomerParentBranch;
import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.service.CustomerArchiveConfigService;
import com.qubedlab.crair.service.CustomerBiometricDetailsService;
import com.qubedlab.crair.service.CustomerContactDetailsService;
import com.qubedlab.crair.service.CustomerIntegrationIDsService;
import com.qubedlab.crair.service.CustomerParentBranchService;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import com.qubedlab.crair.service.CustomerPreviousContactDetailsService;
import com.qubedlab.crair.service.DecodeIDDataService;
import com.qubedlab.crair.service.GlobalIDService;
import com.qubedlab.crair.service.RelativesContactDetailsService;
import com.qubedlab.crair.util.Constants;

@RestController
@RequestMapping("/scan")
@CrossOrigin
public class ScanIdController {

    @Autowired
    private DecodeIDDataService decodeIDDataService;

    @Autowired
    private CustomerPersonalDetailsService temporaryCustomerService;

    @Autowired
    private CustomerParentBranchService customerParentBranchService;

    @Autowired
    private CustomerArchiveConfigService customerArchiveConfigService;

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

    @Autowired
    Producer producer;

    @Autowired
    private GlobalIDService globalIDService;

    @PostMapping(path = "/customerid")
    public Map<String, Object> saveIdData(@RequestBody Map<String, Object> data) {

	String idData = data.get("idData").toString();
	Map<String, Object> responseDataMap = decodeIDDataService.decodeCustomerScannedId(idData);

	return saveCustomerScannedID(responseDataMap);

    }

    @PostMapping(path = "/customer/profile")
    public Map<String, Object> getCustomerProfile(@RequestBody Map<String, Object> data) {

	Map<String, Object> map = new LinkedHashMap<String, Object>();
	Map<String, Object> dataParent = new LinkedHashMap<>();

	String customerGlobalID = data.get(Constants.CUSTOMER_GLOBAL_ID).toString();
	String branchid = data.get(Constants.BRANCHID).toString();
	String parentid = data.get(Constants.PARENTID).toString();

	map.put("customerPersonalDetails", temporaryCustomerService.CustomerByGlobalID(customerGlobalID));
	map.put("customerBiometricDetails",
		CustomerBiometricDetailsService.getCustomerBiometricDetails(customerGlobalID));
	map.put("customerContactDetails", customerContactDetailsService.getCustomerContactDetails(customerGlobalID));
	map.put("customerPreviousContactDetails",
		customerPreviousContactDetailsService.getCustomerPreviousContactDetails(customerGlobalID));
	map.put("customerIntegrationID", customerIntegrationIDsService.listCustomersByGlobalID(customerGlobalID));
	dataParent.put("CustomerData", map);
	return dataParent;

    }

    @GetMapping(path = "/customers")
    public List<CustomerPersonalDetails> customers() {
	return temporaryCustomerService.listCustomers();

    }

    @GetMapping(path = "/customer/dlnumber/{dlNumber}")
    public CustomerPersonalDetails customersByDLNumber(@PathVariable("dlNumber") String dlNumber) {
	List<CustomerPersonalDetails> temporaryCustomerList = temporaryCustomerService
		.listCustomersByDLNumber(dlNumber);

	if (temporaryCustomerList.size() > 0) {
	    return temporaryCustomerService.listCustomersByDLNumber(dlNumber).get(0);

	} else {

	    return null;
	}

    }

    @PostMapping(path = "/customerid/kafka")
    public Map<String, Object> sendIdDataToKafka(@RequestBody Map<String, Object> data) {

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("customerData", data);
	producer.send(data);
	return map;

    }

    @PostMapping(path = "/search/customer")
    public Map<String, Object> searchCustomer(@RequestBody Map<String, Object> data) {

	Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
	List<CustomerPersonalDetails> responseList = new ArrayList<CustomerPersonalDetails>();
	List<CustomerPersonalDetails> responseList2 = new ArrayList<CustomerPersonalDetails>();
	List<CustomerParentBranch> cpbList = customerParentBranchService.listCustomersByBranchId(
		data.get(Constants.PARENTID).toString(), data.get(Constants.BRANCHID).toString());

	cpbList.forEach((cpbObject -> {

	    responseList.add(temporaryCustomerService.CustomerByGlobalID(cpbObject.getCustomerGlobalID()));

	}));

	responseList.forEach((cpbObject -> {
	    String searchVal = data.get("searchvalue").toString().toLowerCase();
	    if (cpbObject.getFirstName().toLowerCase().contains(searchVal)
		    || cpbObject.getLastName().toLowerCase().contains(searchVal)
		    || cpbObject.getLicenseIDNumber().toLowerCase().contains(searchVal)) {
		responseList2.add(cpbObject);
	    }

	}));

	responseMap.put("customer", responseList2);
	return responseMap;

    }

    @PostMapping(path = "/customer/list")
    public Map<String, Object> getCustomerList(@RequestBody Map<String, Object> data) {
	Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
	List<CustomerPersonalDetails> responseList = new ArrayList<CustomerPersonalDetails>();

	List<CustomerParentBranch> cpbList = customerParentBranchService.listCustomersByBranchId(
		data.get(Constants.PARENTID).toString(), data.get(Constants.BRANCHID).toString());

	cpbList.forEach((cpbObject -> {

	    responseList.add(temporaryCustomerService.CustomerByGlobalID(cpbObject.getCustomerGlobalID()));

	}));

	responseMap.put("customerList", responseList);
	return responseMap;
    }

    @PostMapping(path = "/archive/config")
    public Map<String, Object> getArchiveConfig(@RequestBody Map<String, Object> data) {

	Map<String, Object> responseMap = new HashMap<String, Object>();
	CustomerArchiveConfig cacCurrent = customerArchiveConfigService
		.getConfigByParentBranch(data.get("parentid").toString(), data.get("branchid").toString());
	Optional<CustomerArchiveConfig> CustomerArchiveConfig = Optional.ofNullable(cacCurrent);

	if (CustomerArchiveConfig.isPresent()) {
	    responseMap.put("customerArchiveConfig", cacCurrent);

	}
	return responseMap;
    }

    @PostMapping(path = "/save/archive/config")
    public Map<String, Object> saveArchiveConfig(@RequestBody Map<String, Object> data) {

	Map<String, Object> responseMap = new HashMap<String, Object>();
	CustomerArchiveConfig cacCurrent = customerArchiveConfigService
		.getConfigByParentBranch(data.get("parentid").toString(), data.get("branchid").toString());
	Optional<CustomerArchiveConfig> CustomerArchiveConfig = Optional.ofNullable(cacCurrent);

	if (CustomerArchiveConfig.isPresent()) {
	    CustomerArchiveConfig cacUpdate = cacCurrent;
	    cacUpdate.setBranchId(data.get("branchid").toString());
	    cacUpdate.setParentId(data.get("parentid").toString());
	    cacUpdate.setRetrieveRecordsForLastDays(Integer.parseInt(data.get("days").toString()));
	    cacUpdate.setDateLastScanned(LocalDateTime.now());
	    customerArchiveConfigService.saveConfigByParentBranch(cacUpdate);
	    responseMap.put("customerArchiveConfig", cacUpdate);
	    return responseMap;
	} else {
	    CustomerArchiveConfig cacInsert = new CustomerArchiveConfig();
	    cacInsert.setBranchId(data.get("branchid").toString());
	    cacInsert.setParentId(data.get("parentid").toString());
	    cacInsert.setRetrieveRecordsForLastDays(Integer.parseInt(data.get("days").toString()));
	    cacInsert.setDateLastScanned(LocalDateTime.now());
	    customerArchiveConfigService.saveConfigByParentBranch(cacInsert);

	    responseMap.put("customerArchiveConfig", cacInsert);
	    return responseMap;
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
													    // blocked
													    // and no
													    // existing
													    // record

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

	    data.put("customerPersonalDetails", temporaryCustomerWithErrorStatus(responseDataMap, customerGlobalID));
	    data.put("customerBiometricDetails", customerBiometricDetails(responseDataMap, customerGlobalID));
	    data.put("customerContactDetails", customerContactDetails(responseDataMap, customerGlobalID));
	    dataParent.put("CustomerData", data);
	    return dataParent;
	}

    }

}
