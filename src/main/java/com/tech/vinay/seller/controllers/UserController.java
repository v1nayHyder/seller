package com.tech.vinay.seller.controllers;
import com.tech.vinay.seller.entities.User1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/master")
public class UserController {

    @GetMapping("/userList")
    public ResponseEntity<List<User1> > getUserList() {
        List<User1> users1 = null;
        try {
            users1 = Arrays.asList(
                    new User1(1L, "Vinay", 12),
                    new User1(2L, "Ravi", 56),
                    new User1(3L, "VinayPrakashYadav", 14)
            );
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        System.out.println("After aspect++++++++++++++++++");
        if(users1==null){
            throw   new RuntimeException("Bad Request");
        }
        return ResponseEntity.ok(users1);
    }
}
