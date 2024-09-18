package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.UserOtpData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptDataRepository extends JpaRepository<UserOtpData,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_otp_data WHERE phone_number = :mobileNumber ORDER BY created_at desc LIMIT :limit" )
    public List<UserOtpData> getTimeByMobile(@Param("mobileNumber") String mobileNumber , @Param("limit") Integer limit);

    @Query(nativeQuery = true,value = "SELECT * FROM user_otp_data u WHERE u.phone_number = :userId OR u.email = :userId")
    UserOtpData verifyOtp(@Param("userId") String userId);

//    @Query("SELECT u.otp FROM UserOtpData u WHERE u.phoneNumber = :userId OR u.email = :userId")
//    String verifyOtp(@Param("userId") String userId);

}
