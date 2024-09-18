package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ondc_commodity_master")
public class OndcCommodityMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = false)
    private OndcCategoryMaster ondcCategoryMaster;

    @Column(name="description")
    private String description;


    @Column(name = "domain")
    private String domain;


    @Column(name = "tax")
    private Double tax;
}
