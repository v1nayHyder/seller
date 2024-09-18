package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateMaster,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM state_master WHERE id = :stateId")
    StateMaster getByStateId(@Param("stateId") Long stateId);

}
