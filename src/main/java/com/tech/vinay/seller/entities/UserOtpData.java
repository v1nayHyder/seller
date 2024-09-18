package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_otp_data")
public class UserOtpData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer otpDataId;

    @Column(name = "phone_number")
    private  String phoneNumber;

    @Column(name = "email")
    private  String email;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private User user;

    @Column(name = "validated")
    private Boolean validated;

    @Column(name = "otp")
    private String otp;

    @Column(name="expiration_time")
    private Timestamp expirationTime;

}
