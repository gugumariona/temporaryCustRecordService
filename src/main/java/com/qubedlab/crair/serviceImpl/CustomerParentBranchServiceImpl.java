package com.qubedlab.crair.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerArchiveConfig;
import com.qubedlab.crair.models.CustomerParentBranch;
import com.qubedlab.crair.repository.CustomerParentBranchRepository;
import com.qubedlab.crair.service.CustomerArchiveConfigService;
import com.qubedlab.crair.service.CustomerParentBranchService;

@Service
public class CustomerParentBranchServiceImpl implements CustomerParentBranchService {

    @Autowired
    private CustomerParentBranchRepository repo;

    @Autowired
    private CustomerArchiveConfigService customerArchiveConfigService;

    @Override
    public List<CustomerParentBranch> listCustomersByParentId(String parentId) {
	// TODO Auto-generated method stub
	return repo.listCustomersByParentId(parentId);
    }

    @Override
    public List<CustomerParentBranch> listCustomersByBranchId(String parentId, String branchId) {

	CustomerArchiveConfig cac = customerArchiveConfigService.getConfigByParentBranch(parentId, branchId);
	LocalDateTime endDate = LocalDateTime.now();
	LocalDateTime startDate = endDate.minusDays(cac.getRetrieveRecordsForLastDays());
	return repo.listCustomersByBranchId(branchId, startDate, endDate);
    }

    @Override
    public CustomerParentBranch getCustomerByParentBranchGlobalID(String parentId, String branchId,
	    String customerGlobalID) {

	return getCustomerByParentBranchGlobalID(parentId, branchId, customerGlobalID);
    }

    @Override
    public void save(CustomerParentBranch customerParentBranch) {
	repo.save(customerParentBranch);
    }

    @Override
    public List<CustomerParentBranch> listCustomersByParentBranchlicenseIDNumber(String parentId, String branchId,
	    String licenseIDNumber) {

	return repo.listCustomersByParentBranchlicenseIDNumber(parentId, branchId, licenseIDNumber);
    }

}
