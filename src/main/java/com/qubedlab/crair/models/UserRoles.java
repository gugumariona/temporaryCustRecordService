package com.qubedlab.crair.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_roles")
public class UserRoles {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "customer_global_id")
    private String customerGlobalID;

    @Column(name="userId")
    private String userId;

    @Column(name = "role")
    private String role;




}
