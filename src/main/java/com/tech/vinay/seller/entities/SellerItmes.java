package com.tech.vinay.seller.entities;


import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seller_item")
public class SellerItmes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="symbol")
    private String symbol;

    @Column(name = "lot_type")
    private  String lotType;

    @Column(name="lot_size")
    private  Integer lotSize;

    @Column(name = "short_desc",columnDefinition = "TEXT")
    private String shortDesc;

    @Column(name = "long_desc",columnDefinition = "TEXT")
    private String longDesc;

    @Column(name = "time_to_ship_in_days")
    private Integer timeToShip;

    @Column(name = "available_count")
    private Integer availableCount;

    @Column(name = "minimum_count")
    private Integer minimumCount;

    @Column(name="maximum_count")
    private Integer maximumCount;

    @Column(name="fulfillment_type")
    private String fulfillmentType;

    @Column(name = "return_window")
    private Integer returnWindow;

    @Column(name = "cancellable")
    private boolean cancellable;

    @Column(name = "returnable")
    private boolean returnable;

    private String fssai;

    @Column(name = "other_fssai")
    private String otherFssai;

    @Column(name="cash_on_delivery")
    private  boolean cashOnDelivery;

    @Column(name="is_seller_pickup_return")
    private boolean isSellerPickupReturn;

    @Column(name = "length", nullable = false)
    private double length;

    @Column(name = "breadth", nullable = false)
    private double breadth;

    @Column(name = "height", nullable = false)
    private double height;


}
