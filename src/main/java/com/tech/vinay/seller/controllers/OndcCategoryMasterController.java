package com.tech.vinay.seller.controllers;

import com.tech.vinay.seller.entities.OndcCategoryMaster;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.OndcCategoryMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categoryMaster")
public class OndcCategoryMasterController {

    private final OndcCategoryMasterService ondcCategoryMasterService;

    @Autowired
    public OndcCategoryMasterController(OndcCategoryMasterService ondcCategoryMasterService) {
        this.ondcCategoryMasterService = ondcCategoryMasterService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody @Validated OndcCategoryMaster ondcCategoryMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            OndcCategoryMaster savedCategory = ondcCategoryMasterService.createCategory(ondcCategoryMaster);

            if (savedCategory == null) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("Invalid details provided. Category creation failed.");
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Category Master has been created successfully");
            apiResponse.setData(savedCategory);
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
