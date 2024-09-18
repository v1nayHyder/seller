package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="lot_type_master")
public class LotTypeMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Integer id;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name="description", nullable = false)
    private String description;

}
