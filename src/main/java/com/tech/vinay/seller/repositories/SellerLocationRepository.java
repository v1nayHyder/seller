package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.SellerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerLocationRepository extends JpaRepository<SellerLocation,Integer> {

    List<SellerLocation> findBySellerId(Integer sellerId);
}
