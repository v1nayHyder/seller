package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "ondc_category_master")
public class OndcCategoryMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name="is_active")
    private Boolean isActive;


    @Column(name = "code")
    private String code;

    @NotBlank(message = "Category cannot be blank")
    @Column(name = "category")
    private String category;

    @Column(name = "domain")
    private String  domain;

    @Column(name = "package_type")
    private String packageType;

}
