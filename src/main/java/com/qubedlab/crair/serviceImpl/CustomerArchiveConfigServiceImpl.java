package com.qubedlab.crair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerArchiveConfig;
import com.qubedlab.crair.repository.CustomerArchiveConfigRepository;
import com.qubedlab.crair.service.CustomerArchiveConfigService;

@Service
public class CustomerArchiveConfigServiceImpl implements CustomerArchiveConfigService {

    @Autowired
    private CustomerArchiveConfigRepository repo;

    @Override
    public CustomerArchiveConfig getConfigByParentBranch(String parentId, String branchId) {

	return repo.getConfigByParentBranch(parentId, branchId);
    }

    @Override
    public CustomerArchiveConfig saveConfigByParentBranch(CustomerArchiveConfig customerArchiveConfig) {

	return repo.save(customerArchiveConfig);
    }

}
