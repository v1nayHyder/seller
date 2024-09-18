package com.tech.vinay.seller.entities;



import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="\"user\"")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private  String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type;

    @Column(name = "is_active")
    private boolean isActive;


    @Column(name = "domain",nullable = false)
    private String domain="ONDC:AGR11";

}
