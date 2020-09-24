package com.qubedlab.crair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qubedlab.crair.models.CustomerArchiveConfig;

public interface CustomerArchiveConfigRepository extends JpaRepository<CustomerArchiveConfig, Long> {

    @Query("FROM CustomerArchiveConfig WHERE parentId =:parentId AND branchId =:branchId")
    public CustomerArchiveConfig getConfigByParentBranch(String parentId, String branchId);
}
