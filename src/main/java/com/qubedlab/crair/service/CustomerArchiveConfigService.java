package com.qubedlab.crair.service;

import com.qubedlab.crair.models.CustomerArchiveConfig;

public interface CustomerArchiveConfigService {

    public CustomerArchiveConfig getConfigByParentBranch(String parentId, String branchId);

    public CustomerArchiveConfig saveConfigByParentBranch(CustomerArchiveConfig customerArchiveConfig);
}
