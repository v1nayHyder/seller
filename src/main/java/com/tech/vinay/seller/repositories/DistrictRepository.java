package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.DistrictMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictMaster,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM district_master WHERE id = :districtId")
    DistrictMaster getByDistrictId(@Param("districtId")Long districtId);
}
