package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.IfscCodeMaster;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IfscCodeRepository extends JpaRepository<IfscCodeMaster,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM ifsc_code_master WHERE ifsc_code = :ifscCode")

    IfscCodeMaster getByIfScCode(@Param("ifscCode")String upperCase);
}
