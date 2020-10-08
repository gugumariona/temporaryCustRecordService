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
import com.qubedlab.crair.util.Constants;

@Service
public class CustomerParentBranchServiceImpl implements CustomerParentBranchService {

    @Autowired
    private CustomerParentBranchRepository repo;

    @Autowired
    private CustomerArchiveConfigService customerArchiveConfigService;

    @Override
    public List<CustomerParentBranch> listCustomersByParentId(String parentId) {

	return repo.listCustomersByParentId(parentId);
    }

    @Override
    public List<CustomerParentBranch> listCustomersByBranchId(String parentId, String branchId, String userid,
	    String userrole) {

	CustomerArchiveConfig cac = customerArchiveConfigService.getConfigByParentBranch(parentId, branchId);
	LocalDateTime endDate = LocalDateTime.now();
	LocalDateTime startDate = endDate.minusDays(cac.getRetrieveRecordsForLastDays());
	if (userrole.compareToIgnoreCase(Constants.MANAGER) == 0) {
	    return repo.listCustomersByBranchId(branchId, startDate, endDate);
	} else {

	    return repo.listCustomersByBranchIdUserId(branchId, startDate, endDate, userid);

	}

    }

    @Override
    public CustomerParentBranch getCustomerByParentBranchGlobalID(String parentId, String branchId,
	    String customerGlobalID) {

	return getCustomerByParentBranchGlobalID(parentId, branchId, customerGlobalID);
    }

    @Override
    public CustomerParentBranch save(CustomerParentBranch customerParentBranch) {
	return repo.save(customerParentBranch);
    }

    @Override
    public List<CustomerParentBranch> listCustomersByParentBranchlicenseIDNumber(String parentId, String branchId,
	    String licenseIDNumber) {

	return repo.listCustomersByParentBranchlicenseIDNumber(parentId, branchId, licenseIDNumber);
    }

}
