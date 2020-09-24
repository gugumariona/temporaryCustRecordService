package com.qubedlab.crair.service;

import java.util.List;

import com.qubedlab.crair.models.CustomerParentBranch;

public interface CustomerParentBranchService {

    public List<CustomerParentBranch> listCustomersByParentId(String parentId);

    public List<CustomerParentBranch> listCustomersByBranchId(String parentId, String branchId);

    public CustomerParentBranch getCustomerByParentBranchGlobalID(String parentId, String branchId,
	    String customerGlobalID);

    public void save(CustomerParentBranch customerParentBranch);

    public List<CustomerParentBranch> listCustomersByParentBranchlicenseIDNumber(String parentId, String branchId,
	    String licenseIDNumber);
}
