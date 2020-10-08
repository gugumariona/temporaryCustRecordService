package com.qubedlab.crair.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerParentBranch;
import com.qubedlab.crair.repository.CustomerParentBranchRepository;

class CustomerParentBranchServiceImplTesting {

    @InjectMocks
    CustomerParentBranchServiceImpl service;

    @Mock
    private CustomerParentBranchRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
	CustomerParentBranch cpb = new CustomerParentBranch();
	cpb.setBranchId("1509931");
	cpb.setParentId("1509932");
	cpb.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpb.setLicenseIDNumber("N123445dds");

	when(repo.save(cpb)).thenReturn(cpb);
	assertEquals(cpb, service.save(cpb));
    }

    @Test
    void testListCustomersByParentBranchlicenseIDNumber() {
	CustomerParentBranch cpb = new CustomerParentBranch();
	cpb.setBranchId("1509931");
	cpb.setParentId("1509932");
	cpb.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	cpb.setLicenseIDNumber("N123445dds");

	when(repo.listCustomersByParentBranchlicenseIDNumber("1509932", "1509931", "N123445dds"))
		.thenReturn(Stream.of(cpb).collect(Collectors.toList()));
	assertEquals(1, service.listCustomersByParentBranchlicenseIDNumber("1509932", "1509931", "N123445dds").size());
    }

}
