package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.qubedlab.crair.models.RelativesContactDetails;
import com.qubedlab.crair.repository.RelativesContactDetailsRepository;

class RelativesContactDetailsServiceImplTest {

    @InjectMocks
    RelativesContactDetailsServiceImpl service;

    @Mock
    RelativesContactDetailsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRelativesContactDetails() {

	RelativesContactDetails rcd = new RelativesContactDetails();
	rcd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	rcd.setRelationshipToRelative("wife");
	rcd.setRelativeAddress("22 nicholson street");
	rcd.setRelativeCity("New York");
	rcd.setRelativeFirstName("Florence");
	rcd.setRelativeLastName("Martins");
	rcd.setRelativePhone("4456987");
	rcd.setRelativePostalCode("12365547");
	rcd.setRelativeState("NY");

	Optional<RelativesContactDetails> rcdOPOptional = Optional.of(rcd);

	when(repo.findById("aic94804juldd22cam7891n6429082grn")).thenReturn(rcdOPOptional);
    }

    @Test
    void testSaveRelativesContactDetails() {
	RelativesContactDetails rcd = new RelativesContactDetails();
	rcd.setCustomerGlobalID("aic94804juldd22cam7891n6429082grn");
	rcd.setRelationshipToRelative("wife");
	rcd.setRelativeAddress("22 nicholson street");
	rcd.setRelativeCity("New York");
	rcd.setRelativeFirstName("Florence");
	rcd.setRelativeLastName("Martins");
	rcd.setRelativePhone("4456987");
	rcd.setRelativePostalCode("12365547");
	rcd.setRelativeState("NY");

	when(repo.save(rcd)).thenReturn(rcd);
	assertEquals(rcd, service.saveRelativesContactDetails(rcd));
    }

}
