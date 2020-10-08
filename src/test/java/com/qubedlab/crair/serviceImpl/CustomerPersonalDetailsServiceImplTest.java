package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.repository.CustomerPersonalDetailsRepository;

class CustomerPersonalDetailsServiceImplTest {

    @InjectMocks
    CustomerPersonalDetailsServiceImpl service;

    @Mock
    private CustomerPersonalDetailsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListCustomersByDLNumber() {
	CustomerPersonalDetails cpd = new CustomerPersonalDetails();
	cpd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpd.setDateCreated(LocalDate.now());
	cpd.setFirstName("Jane");
	cpd.setLastName("Djokovic");
	cpd.setLicenseIDNumber("NL0012345M");

	when(repo.listCustomersByDLNumber("NL0012345M")).thenReturn(Stream.of(cpd).collect(Collectors.toList()));
	assertEquals(1, service.listCustomersByDLNumber("NL0012345M").size());
    }

    @Test
    void testSaveCustomer() {
	CustomerPersonalDetails cpd = new CustomerPersonalDetails();
	cpd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpd.setDateCreated(LocalDate.now());
	cpd.setFirstName("Jane");
	cpd.setLastName("Djokovic");
	cpd.setLicenseIDNumber("NL0012345M");

	when(repo.save(cpd)).thenReturn(cpd);
	assertEquals(cpd.getCustomerGlobalID(), service.saveCustomer(cpd).getCustomerGlobalID());

    }

}
