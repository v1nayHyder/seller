package com.tech.vinay.seller.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.vinay.pojo.login.GenerateOtpRequest;
import com.tech.vinay.pojo.login.VerifyOtpRequest;
import com.tech.vinay.seller.entities.UserOtpData;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.OtpDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/rest/v1/otp")
public class OtpController {

    private @Autowired OtpDataService otpDataService;

    @PostMapping(value = "/generateOtp")
    public ApiResponse generateOtp(@RequestBody @Valid GenerateOtpRequest otpRequest){
        ApiResponse apiResponse=new ApiResponse();
        System.out.println("+++++++++++++"+otpRequest.getPhoneNumber());
        System.out.println("+++++++++++++"+otpRequest.getEmail());
//        try {
////            ObjectMapper objectMapper=new ObjectMapper();
////            objectMapper.readValue(generateOtpRequest, GenerateOtpRequest.class);
//         if(otpRequest==null){
//             apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
//             apiResponse.setMessage("Please enter valid request for otp Generation");
//         }
        try{

         if(otpRequest.getPhoneNumber()!=null){
             otpDataService.verifyIfMobileNumberAlreadyRegistered(otpRequest);
             UserOtpData userOtpData=otpDataService.generateOtpAndSave(otpRequest,"General OTP ONDC");
             System.out.println("2");

             if (Objects.nonNull(userOtpData.getOtpDataId())) {
                 System.out.println("3");

                 apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                 apiResponse.setMessage("OTP sent on mobile no. "+userOtpData.getPhoneNumber());

             }
             }
         else if (otpRequest.getEmail()!=null){
             UserOtpData userOtpData=otpDataService.generateOtpAndSave(otpRequest,"General OTP ONDC");
            if (userOtpData.getOtpDataId()>0){
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("OTP sent on email. "+userOtpData.getEmail());
                }
                }
         else {
                 apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                 apiResponse.setMessage("Kindly provide email or mobile no.");
               }

        }
        catch (Exception e){
            e.printStackTrace();
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage(e.getMessage());
        }

     return  apiResponse;
    }
//
//    @PostMapping(path = "/verifyOtp")
//    public ApiResponse verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest){
//        ApiResponse apiResponse=new ApiResponse();
//    }
}
