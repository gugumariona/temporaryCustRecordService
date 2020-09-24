package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customer_integration_ids")
public class CustomerIntegrationIDs {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "customer_integration_id")
    private String customerIntegrationID;

    @Column(name = "customer_integration_company")
    private String customerIntegrationCompany;
}
