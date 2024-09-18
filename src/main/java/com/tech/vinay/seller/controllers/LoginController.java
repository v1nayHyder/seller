package com.tech.vinay.seller.controllers;

import com.tech.vinay.pojo.login.LoginRequest;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchProviderException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/")
public class LoginController {
    private @Autowired LoginService loginService;
    @PostMapping(value = "/login")
    public ApiResponse getLogin(@RequestBody @Valid LoginRequest loginRequest) throws NoSuchProviderException {
        ApiResponse response=new ApiResponse();
        try {
            if(Objects.isNull(loginRequest.getPassword())&&Objects.isNull(loginRequest.getOtp())){
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
                response.setMessage("Please enter otp/password");
            }
            response=loginService.loginByUsernameOrMobileNumber(loginRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
//            response.setMessage("You have logged in successfully");

        return  response;
    }



    @PostMapping(value = "/forgot")
    public ApiResponse forgotPassword(@RequestParam String username, @RequestParam String password) {
        ApiResponse response = new ApiResponse();

        try {
            response = loginService.resetPassword(username, password);
        } catch (Exception e) {

            throw new RuntimeException("Somethings went wrong");

        }

        return response;
    }
}
