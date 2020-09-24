package com.qubedlab.crair.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "relatives_contact_details")
public class RelativesContactDetails {

    @Id
    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name = "relative_last_name")
    private String relativeLastName;

    @Column(name = "relativeFirstName")
    private String relativeFirstName;

    @Column(name = "relationship_to_relative")
    private String relationshipToRelative;

    @Column(name = "relative_phone")
    private String relativePhone;

    @Column(name = "relative_address")
    private String relativeAddress;

    @Column(name = "relative_address2")
    private String relativeAddress2;

    @Column(name = "relative_city")
    private String relativeCity;

    @Column(name = "relative_state")
    private String relativeState;

    @Column(name = "relative_postal_code")
    private String relativePostalCode;

}
