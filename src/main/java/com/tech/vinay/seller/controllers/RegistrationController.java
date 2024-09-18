package com.tech.vinay.seller.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tech.vinay.pojo.login.SellerBankDetailsRequest;
import com.tech.vinay.pojo.login.SellerBankDetailsResponse;
import com.tech.vinay.pojo.login.UserRegistrationRequest;
import com.tech.vinay.seller.entities.SellerBankDetails;
import com.tech.vinay.seller.entities.User;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.BankDetailsService;
import com.tech.vinay.seller.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
public class RegistrationController {
    private @Autowired BankDetailsService bankDetailsService;
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/register", consumes = "multipart/form-data", produces = "application/json")
    public ApiResponse registrationUser(@RequestPart("userRegistrationRequest") String userRegistrationRequestJson) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            UserRegistrationRequest userRegistrationRequest = objectMapper.readValue(userRegistrationRequestJson, UserRegistrationRequest.class);
            User user = registrationService.registration(userRegistrationRequest);
            if (Objects.nonNull(user.getId())) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("User is successfully created");
                apiResponse.setData(userRegistrationRequest);
            } else {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("User is not created");
            }
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
    @PostMapping(value = "/bankDetails",consumes = "multipart/form-data",produces = "application/json")
    public  ApiResponse createBankDetails(@RequestPart("sellerBankDetailsRequest") String sellerBankDetailsRequestJson){
        ApiResponse response =new ApiResponse();
         try {
             ObjectMapper objectMapper=new ObjectMapper();
             objectMapper.registerModule(new JavaTimeModule());
             SellerBankDetailsRequest sellerBankDetailsRequest=objectMapper.readValue(sellerBankDetailsRequestJson,SellerBankDetailsRequest.class);
             SellerBankDetails sellerBankDetails=bankDetailsService.createBankDetails(sellerBankDetailsRequest);
             if(Objects.nonNull(sellerBankDetails   )){
                 response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                 response.setMessage("Bank details created successfully");
                 response.setData(sellerBankDetails);
             }
             else {
                 response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                 response.setMessage("Seller bank details not created");

             }
         }
         catch (Exception e){
             response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
             response.setMessage(e.getMessage());
         }
         return response;
    }
    @DeleteMapping(value = "/bankDetails/{sellerBankDetailsId}")
    public  ApiResponse deleteBankDetails(@PathVariable("sellerBankDetailsId") Integer sellerBankDetailsId){
        ApiResponse response =new ApiResponse();
        try {
            SellerBankDetails sellerBankDetails=bankDetailsService.deleteBankDetails(sellerBankDetailsId);
            System.out.println(sellerBankDetails.toString()+"+++++++++++++++++++++++");
            if(Objects.nonNull(sellerBankDetails)){
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                response.setMessage("Bank details deleted successfully");
//                response.setData(sellerBankDetails);
            }
            else {
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                response.setMessage("Seller bank details not deleted");
            }
        }
        catch (Exception e){
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping(value = "/bankDetails",consumes = "multipart/form-data",produces = "application/json")
    public ApiResponse updateBankDetails(@RequestPart("bankDetails")@Valid String sellerBankDetailsRequestJson) throws JsonProcessingException {
        ApiResponse response=new ApiResponse();
       try {
           ObjectMapper objectMapper=new ObjectMapper();
           SellerBankDetailsRequest sellerBankDetailsRequest=objectMapper.readValue(sellerBankDetailsRequestJson, SellerBankDetailsRequest.class);
           SellerBankDetails sellerBankDetails=bankDetailsService.updateBankDetails(sellerBankDetailsRequest);
           if(Objects.nonNull(sellerBankDetails)){
               response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
               response.setMessage("Seller Bank Details updated successfully");
//               response.setData(sellerBankDetails);
           }
           else {
               response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
               response.setMessage("Seller Bank details not updated");

           }
       }
       catch (Exception e){
           response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
           response.setMessage(e.getMessage());
       }
       return  response;
    }

    @GetMapping(value = "/bankDetails/{sellerBankDetailsId}")
    public  ApiResponse getBankDetails(@PathVariable("sellerBankDetailsId") Integer sellerBankDetailsId){
        ApiResponse response=new ApiResponse();
       try {
           SellerBankDetailsResponse sellerBankDetailsResponse=bankDetailsService.getBankDetails(sellerBankDetailsId);
           if (Objects.nonNull(sellerBankDetailsResponse)){
               response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
               response.setMessage("Seller Bank Details fetched successfully");
               response.setData(sellerBankDetailsResponse);
           }
           else {
               response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
               response.setMessage("Seller Personal Details not found");
           }
       }
       catch (Exception e){
           response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
           response.setMessage("Getting error while processing");
       }
        return response;
    }
}
