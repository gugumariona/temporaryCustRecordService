package com.qubedlab.crair.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customer_parent_branch")
public class CustomerParentBranch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "parentid")
    private String parentId;

    @Column(name = "branchid")
    private String branchId;

    @Column(name = "License_ID_Number")
    private String licenseIDNumber;

    @Column(name = "date_last_scanned")
    private LocalDateTime dateLastScanned;

    @Column(name = "user_id")
    private String userId;

}
