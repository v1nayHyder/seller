package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "seller_fulfillment")
public class Fulfillment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @Column(name = "is_active")
    private  boolean isActive;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "type")
    private  String type;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    private  String email;

    @Column(name = "ondc_org_provider_name")
    private String ondcOrgProName;

    @Column(name = "is_tracking")
    private  boolean isTracking;

    @Column(name="ondc_org_category")
    private String ondcOrgCategory;

    @Column(name = "ondc_org_tat ")
    private  String ondcOrgTat;

}
