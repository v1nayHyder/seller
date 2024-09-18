package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.CityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityMaster,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM city_master WHERE id = :cityId")
    public CityMaster getByCityId(@Param("cityId") Long cityId);
}
