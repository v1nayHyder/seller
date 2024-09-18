package com.tech.vinay.seller.controllers;

import com.tech.vinay.seller.entities.OndcCommodityMaster;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.OndcCommodityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/api/v1/commodityMaster")
public class OndcCommodityMasterController {

    private @Autowired OndcCommodityMasterService ondcCommodityMasterService;
    @PostMapping("/addCommodity")
    public ResponseEntity<ApiResponse> addCommodity(@RequestBody @Validated OndcCommodityMaster ondcCommodityMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            OndcCommodityMaster savedCommodity = ondcCommodityMasterService.createCategory(ondcCommodityMaster);

            if (savedCommodity == null) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("Invalid details provided. Category creation failed.");
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Category Master has been created successfully");
            apiResponse.setData(savedCommodity);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Invalid input: " + e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
