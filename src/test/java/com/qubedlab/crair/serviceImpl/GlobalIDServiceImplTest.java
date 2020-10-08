package com.qubedlab.crair.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class GlobalIDServiceImplTest {

    @InjectMocks
    GlobalIDServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLastNameReverse() {

	assertEquals("hti", service.lastNameReverse("Smith"));
    }

    @Test
    void testMonthOfBirth() {
	assertEquals("mar", service.monthOfBirth("1987-03-12"));
    }

    @Test
    void testExtractFirstAndLastLettersFirstName() {
	assertEquals("my", service.extractFirstAndLastLettersFirstName("Mary"));
    }

    @Test
    void testDayOfBirth() {
	assertEquals("31", service.dayOfBirth("1987-03-31"));

	assertEquals("01", service.dayOfBirth("1987-03-01"));
    }

    @Test
    void testGender() {
	assertEquals("f", service.gender("2"));

	assertEquals("m", service.gender("1"));
    }

    @Test
    void testYearOfBirth() {
	assertEquals("7891", service.yearOfBirth("1987-03-12"));
    }

    @Test
    void testDriversLicenseNumberExtract() {
	assertEquals("12365", service.driversLicenseNumberExtract("12365478"));
    }

    @Test
    void testCustomerHeight() {

	assertEquals("508", service.customerHeight("508"));
    }

}
