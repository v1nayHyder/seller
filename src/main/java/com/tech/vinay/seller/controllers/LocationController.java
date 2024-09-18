package com.tech.vinay.seller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tech.vinay.pojo.ondc.location.SellerLocationRequest;
import com.tech.vinay.seller.entities.SellerLocation;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/addLocation")
    public ResponseEntity<ApiResponse> createLocation(
            @Valid @RequestBody SellerLocationRequest sellerLocationRequest) {
        ApiResponse response = new ApiResponse();
        try {
            if (sellerLocationRequest == null) {
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                response.setMessage("Request body cannot be null");
            } else {
                SellerLocation sellerLocation = locationService.createSellerLocation(sellerLocationRequest);
                if (sellerLocation != null) {
                    response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                    response.setMessage("Seller location has been created successfully");
                } else {
                    response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                    response.setMessage("Failed to create seller location");
                }
            }
        } catch (Exception e) {
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("An error occurred while adding location: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{sellerId}")
    public ApiResponse getAllLocation(@PathVariable Integer sellerId){
        ApiResponse apiResponse=new ApiResponse();
        try {

            List<SellerLocation> sellerLocations=locationService.getAllLocationBySellerId(sellerId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Successfully retrieved seller locations.");
            apiResponse.setData(sellerLocations);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Error occurs which getting seller Location: "+e.getMessage());
        }
        return apiResponse;
    }
}
