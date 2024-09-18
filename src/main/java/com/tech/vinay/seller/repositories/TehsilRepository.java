package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.TehsilMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TehsilRepository extends JpaRepository<TehsilMaster,Long> {
        @Query(nativeQuery = true, value = "SELECT * FROM tehsil_master WHERE id = :tehsilId")
        TehsilMaster getByTehsilId(@Param("tehsilId") Long tehsilId);
}
