package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "seller_location")
public class SellerLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private  String name;

    @Column(name = "gps")
    private String gps;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id",referencedColumnName = "id",nullable = false)
    private Seller seller;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "street")
    private String street;

    @Column(name = "snapshot_generated_at")
    private Timestamp snapshotGeneratedAt;

    @Column(name = "state")
    private String  state;

    @Column(name = "days")
    private String days;

    @Column(name = "district")
    private String district;

    @Column(name = "tehsil")
    private  String tehsil;

    @Column(name = "locality")
    private String locality;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name="is_closed")
    private boolean isClosed;

    @Column(name = "start_time")
    private Time stateTime;

    @Column(name = "end_time")
    private  Time endTime;

    @Column(name="consumer_care_contact_name")
    private String consumerCareContactName;

    @Column(name="consumer_care_contact_email")
    private String consumerCareContactEmail;

    @Column(name="consumer_care_phone")
    private String  consumerCarePhone;

    @Column(name="stdCode")
    private String stdCode;



}
