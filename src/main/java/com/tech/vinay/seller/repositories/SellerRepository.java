package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller getByMobile(String mobile);

    Seller getByEmail(String userId);
}
