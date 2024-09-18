package com.tech.vinay.seller.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name="seller")
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name="mobile")
    private String mobile;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "gender")
    private String gender;


    @Column(name = "ttl")
    private Timestamp ttl;

    @Column(name = "snapshot_generated_at")
    private Timestamp snapshotGeneratedAt;

    @Column(name = "long_desc")
    private String longDesc;

    @Column(name = "short_desc")
    private String shortDesc;


    @Column(name = "id_proof_url")
    private String idProofUrl;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_id_number")
    private String photoIdNumber;

    @Column(name = "photo_id_type")
    private String photoIdType;

    @Column(name = "post")
    private String post;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private CityMaster cityMaster;

    @Column(name = "pin_code")
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tehsil_id", referencedColumnName = "id", nullable = false)
    private TehsilMaster tehsilMaster;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd]")
    @Column(name = "dob")
    private LocalDate dob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    private DistrictMaster districtMaster;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "mandi")
    private String mandi;

    @Column(name = "gst_number")
    private String gstNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private StateMaster stateMaster;

}
