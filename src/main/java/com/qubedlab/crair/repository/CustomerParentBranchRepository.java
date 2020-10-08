package com.qubedlab.crair.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qubedlab.crair.models.CustomerParentBranch;

@Transactional
public interface CustomerParentBranchRepository extends JpaRepository<CustomerParentBranch, Long> {

    @Query("FROM CustomerParentBranch WHERE parentId =:parentId ORDER BY date_last_scanned DESC")
    public List<CustomerParentBranch> listCustomersByParentId(String parentId);

    @Query("FROM CustomerParentBranch WHERE branchId =:branchId AND  date_last_scanned BETWEEN :startDate AND :endDate ORDER BY date_last_scanned DESC")
    public List<CustomerParentBranch> listCustomersByBranchId(String branchId, LocalDateTime startDate,
	    LocalDateTime endDate);

    @Query("FROM CustomerParentBranch WHERE branchId =:branchId AND date_last_scanned BETWEEN :startDate AND :endDate  AND userId =:userId ORDER BY date_last_scanned DESC")
    public List<CustomerParentBranch> listCustomersByBranchIdUserId(String branchId, LocalDateTime startDate,
	    LocalDateTime endDate, String userId);

    @Query("FROM CustomerParentBranch WHERE parentId =:parentId AND branchId =:branchId AND customerGlobalID =:customerGlobalID ORDER BY date_last_scanned DESC")
    public CustomerParentBranch getCustomerByParentBranchGlobalID(String parentId, String branchId,
	    String customerGlobalID);

    @Query("FROM CustomerParentBranch WHERE parentId =:parentId AND branchId =:branchId AND licenseIDNumber =:licenseIDNumber ORDER BY date_last_scanned DESC")
    public List<CustomerParentBranch> listCustomersByParentBranchlicenseIDNumber(String parentId, String branchId,
	    String licenseIDNumber);
}
