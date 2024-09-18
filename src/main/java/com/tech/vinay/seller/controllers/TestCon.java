package com.tech.vinay.seller.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.vinay.seller.models.TestDtoRequest;

import com.tech.vinay.seller.models.TestDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestCon {

    @PostMapping(value = "/t", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<TestDtoResponse> test(@RequestPart("testRequest") String testRequest) {
        try {
            // Convert the String to TestRequest object
            ObjectMapper objectMapper = new ObjectMapper();
            TestDtoRequest testRequestObj = objectMapper.readValue(testRequest, TestDtoRequest.class);

            // Create a new TestResponse object
            TestDtoResponse testResponse = new TestDtoResponse();
            testResponse.setName(testRequestObj.getName());
            testResponse.setEmail(testRequestObj.getEmail());

            return new ResponseEntity<>(testResponse, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // Handle JSON parsing error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
