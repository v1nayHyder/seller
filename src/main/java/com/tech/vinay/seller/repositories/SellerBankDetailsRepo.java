package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.SellerBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerBankDetailsRepo extends JpaRepository<SellerBankDetails,Integer> {
}
