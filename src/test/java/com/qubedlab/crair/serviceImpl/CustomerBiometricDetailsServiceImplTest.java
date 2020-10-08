package com.qubedlab.crair.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.CustomerBiometricDetails;
import com.qubedlab.crair.repository.CustomerBiometricDetailsRepository;

class CustomerBiometricDetailsServiceImplTest {

    @InjectMocks
    CustomerBiometricDetailsServiceImpl service;

    @Mock
    private CustomerBiometricDetailsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCustomerBiometricDetails() {

	CustomerBiometricDetails cbdEntity = new CustomerBiometricDetails();
	cbdEntity.setEyeColor("bro");
	cbdEntity.setHairColor("bro");
	cbdEntity.setHeight_in_FT_IN("170");
	cbdEntity.setSex("2");
	cbdEntity.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");

	Optional<CustomerBiometricDetails> cbdOptional = Optional.of(cbdEntity);

	when(repo.findById("aic94804juldd22cam7891n6429082grn")).thenReturn(cbdOptional);
	CustomerBiometricDetails cbd = service.getCustomerBiometricDetails("aic94804juldd22cam7891n6429082grn");
	assertNotNull(cbd);
	assertEquals("bro", cbd.getEyeColor());

    }

    @Test
    public void saveCustomerBiometricDetails() {

	CustomerBiometricDetails cbdEntity = new CustomerBiometricDetails();
	cbdEntity.setEyeColor("bro");
	cbdEntity.setHairColor("bro");
	cbdEntity.setHeight_in_FT_IN("170");
	cbdEntity.setSex("2");
	cbdEntity.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");

	when(repo.save(cbdEntity)).thenReturn(cbdEntity);

	assertEquals(cbdEntity, service.saveCustomerBiometricDetails(cbdEntity));

    }

}
