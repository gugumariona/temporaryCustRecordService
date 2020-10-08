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

import com.qubedlab.crair.models.PDF417AbbreviationsDescriptions;
import com.qubedlab.crair.repository.PDF417AbbreviationsDescriptionsRepository;

class PDF417AbbreviationsDescriptionsServiceImplTest {

    @InjectMocks
    PDF417AbbreviationsDescriptionsServiceImpl service;

    @Mock
    PDF417AbbreviationsDescriptionsRepository repo;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllPDF417Codes() {

	PDF417AbbreviationsDescriptions pad = new PDF417AbbreviationsDescriptions();
	pad.setAbbreviation("ABA");
	pad.setDescription("LAST_NAME");

	when(repo.findAll()).thenReturn(Stream.of(pad).collect(Collectors.toList()));

	assertEquals(Boolean.TRUE, service.getAllPDF417Codes().containsValue("LAST_NAME"));
    }

}
