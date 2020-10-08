package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerPreviousContactDetails;
import com.qubedlab.crair.repository.CustomerPreviousContactDetailsRepository;

class CustomerPreviousContactDetailsImplTest {

    @InjectMocks
    CustomerPreviousContactDetailsImpl service;

    @Mock
    private CustomerPreviousContactDetailsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCustomerPreviousContactDetails() {
	CustomerPreviousContactDetails cpc = new CustomerPreviousContactDetails();
	cpc.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpc.setPrev_2_City("New York");
	cpc.setPrev_2_County("USA");
	cpc.setPrev_2_Postal_Code("45631");

	Optional<CustomerPreviousContactDetails> cpcOPtional = Optional.of(cpc);
	when(repo.findById("aic94804juldd22cam7891n6429082grn")).thenReturn(cpcOPtional);
	assertEquals(cpc.getCustomerGlobalID(),
		service.getCustomerPreviousContactDetails("aic94804juldd22cam7891n6429082grn").getCustomerGlobalID());
    }

    @Test
    void testSaveCustomerPreviousContactDetails() {

	CustomerPreviousContactDetails cpc = new CustomerPreviousContactDetails();
	cpc.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpc.setPrev_2_City("New York");
	cpc.setPrev_2_County("USA");
	cpc.setPrev_2_Postal_Code("45631");

	when(repo.save(cpc)).thenReturn(cpc);
	assertEquals(cpc, service.saveCustomerPreviousContactDetails(cpc));

    }

}
