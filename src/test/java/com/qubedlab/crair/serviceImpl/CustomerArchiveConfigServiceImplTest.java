package com.qubedlab.crair.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerArchiveConfig;
import com.qubedlab.crair.repository.CustomerArchiveConfigRepository;

class CustomerArchiveConfigServiceImplTest {

    @InjectMocks
    CustomerArchiveConfigServiceImpl service;

    @Mock
    private CustomerArchiveConfigRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetConfigByParentBranch() {
	CustomerArchiveConfig cac = new CustomerArchiveConfig();
	cac.setBranchId("1509931");
	cac.setDateLastScanned(LocalDateTime.now());
	cac.setParentId("1509932");
	cac.setRetrieveRecordsForLastDays(15);

	when(repo.getConfigByParentBranch("1509932", "1509931")).thenReturn(cac);

	assertEquals(cac.getBranchId(), service.getConfigByParentBranch("1509932", "1509931").getBranchId());
    }

    @Test
    void testSaveConfigByParentBranch() {
	CustomerArchiveConfig cac = new CustomerArchiveConfig();
	cac.setBranchId("1509931");
	cac.setDateLastScanned(LocalDateTime.now());
	cac.setParentId("1509932");
	cac.setRetrieveRecordsForLastDays(15);

	when(repo.save(cac)).thenReturn(cac);
	assertEquals(cac, service.saveConfigByParentBranch(cac));

    }

}
