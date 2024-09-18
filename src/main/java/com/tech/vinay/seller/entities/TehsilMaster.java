package com.tech.vinay.seller.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "tehsil_master")
public class TehsilMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    private DistrictMaster DistrictMaster;

}