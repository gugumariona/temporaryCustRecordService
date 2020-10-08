package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerIntegrationIDs;
import com.qubedlab.crair.repository.CustomerIntegrationIDsRepository;

class CustomerIntegrationIDsServiceImplTest {

    @InjectMocks
    CustomerIntegrationIDsServiceImpl service;

    @Mock
    private CustomerIntegrationIDsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListCustomersByGlobalID() {

	CustomerIntegrationIDs cd = new CustomerIntegrationIDs();
	cd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cd.setCustomerIntegrationCompany("CDK");
	cd.setCustomerIntegrationID("1236541789QWE");
	when(repo.listCustomersByGlobalID("aic94804juldd22cam7891n6429082grn"))
		.thenReturn(Stream.of(cd).collect(Collectors.toList()));

	assertEquals(1, service.listCustomersByGlobalID("aic94804juldd22cam7891n6429082grn").size());
    }

    @Test
    void testSaveCustomerIntegrationIDs() {
	CustomerIntegrationIDs cd = new CustomerIntegrationIDs();
	cd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cd.setCustomerIntegrationCompany("CDK");
	cd.setCustomerIntegrationID("1236541789QWE");

	when(repo.save(cd)).thenReturn(cd);
	assertEquals(cd, service.saveCustomerIntegrationIDs(cd));

    }

}
