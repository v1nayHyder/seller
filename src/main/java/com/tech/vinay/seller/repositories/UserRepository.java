package com.tech.vinay.seller.repositories;

import com.tech.vinay.seller.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String username);

    @Query("SELECT password FROM User u WHERE u.username = :userId")
    String findPassword(@Param("userId") String userId);


//    User findUser(String userId);
}
