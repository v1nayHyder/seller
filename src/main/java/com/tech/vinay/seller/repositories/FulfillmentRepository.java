package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.Fulfillment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FulfillmentRepository  extends JpaRepository<Fulfillment,Long> {

    // Correct return type and query for checking existence
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM seller_fulfillment WHERE seller_id = :sellerId AND type = :type")
    boolean isExistsBySellerAndType(@Param("sellerId")Integer sellerId, @Param("type") String type);

    // Correct return type for fetching the record
    @Query(nativeQuery = true, value = "SELECT * FROM seller_fulfillment WHERE seller_id = :sellerId AND type = :type")
    Fulfillment getBySellerIdAndType(@Param("sellerId") Integer sellerId, @Param("type") String type);
}