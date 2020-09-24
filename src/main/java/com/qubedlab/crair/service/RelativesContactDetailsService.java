package com.qubedlab.crair.service;

import com.qubedlab.crair.models.RelativesContactDetails;

public interface RelativesContactDetailsService {

    public RelativesContactDetails getRelativesContactDetails(String globalId);

    public RelativesContactDetails saveRelativesContactDetails(RelativesContactDetails relativesContactDetails);
}
