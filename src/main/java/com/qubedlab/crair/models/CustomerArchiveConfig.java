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
@Table(name = "customer_archive_config")
public class CustomerArchiveConfig {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "parentid")
    private String parentId;

    @Column(name = "branchid")
    private String branchId;

    @Column(name = "retrieve_records_for_last_days")
    private int retrieveRecordsForLastDays;

    @Column(name = "date_last_updated")
    private LocalDateTime dateLastScanned;

}
