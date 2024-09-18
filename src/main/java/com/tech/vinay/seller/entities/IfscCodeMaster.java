package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="ifsc_code_master")
public class IfscCodeMaster extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "bank")
    private String bank;

    @Column(name = "branch")
    private String branch;

    @Column(name = "address")
    private String address;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;
}
