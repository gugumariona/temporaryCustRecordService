package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerContactDetails;
import com.qubedlab.crair.repository.CustomerContactDetailsRepository;

class CustomerContactDetailsServiceImplTest {

    @InjectMocks
    CustomerContactDetailsServiceImpl service;

    @Mock
    private CustomerContactDetailsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCustomerContactDetails() {

	CustomerContactDetails ccd = new CustomerContactDetails();
	ccd.setCellPhone("555-6632");
	ccd.setCity("New York");
	ccd.setCounty("USA");
	Optional<CustomerContactDetails> ccdOptional = Optional.of(ccd);
	ccd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	when(repo.findById("aic94804juldd22cam7891n6429082grn")).thenReturn(ccdOptional);
	assertEquals(ccd.getCustomerGlobalID(),
		service.getCustomerContactDetails("aic94804juldd22cam7891n6429082grn").getCustomerGlobalID());
    }

    @Test
    void testSaveCustomerContactDetails() {
	CustomerContactDetails ccd = new CustomerContactDetails();
	ccd.setCellPhone("555-6632");
	ccd.setCity("New York");
	ccd.setCounty("USA");
	ccd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	when(repo.save(ccd)).thenReturn(ccd);
	assertEquals(ccd.getCustomerGlobalID(), service.saveCustomerContactDetails(ccd).getCustomerGlobalID());
    }

}
