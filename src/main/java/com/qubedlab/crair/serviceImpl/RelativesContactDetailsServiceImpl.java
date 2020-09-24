package com.qubedlab.crair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.RelativesContactDetails;
import com.qubedlab.crair.repository.RelativesContactDetailsRepository;
import com.qubedlab.crair.service.RelativesContactDetailsService;

@Service
public class RelativesContactDetailsServiceImpl implements RelativesContactDetailsService {

    @Autowired
    private RelativesContactDetailsRepository repo;

    @Override
    public RelativesContactDetails getRelativesContactDetails(String globalId) {
	// TODO Auto-generated method stub
	return repo.findById(globalId).get();
    }

    @Override
    public RelativesContactDetails saveRelativesContactDetails(RelativesContactDetails relativesContactDetails) {
	// TODO Auto-generated method stub
	return repo.save(relativesContactDetails);
    }

}
