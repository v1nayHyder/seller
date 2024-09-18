package com.tech.vinay.seller.models;

import com.tech.vinay.seller.entities.OndcCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OndcCategoryMasterRepository extends JpaRepository<OndcCategoryMaster,Integer> {
}
