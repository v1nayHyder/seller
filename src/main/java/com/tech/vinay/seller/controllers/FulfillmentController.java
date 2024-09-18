package com.tech.vinay.seller.controllers;

import com.tech.vinay.pojo.ondc.FulfillmentRequest;
import com.tech.vinay.seller.entities.Fulfillment;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.FulfillmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(path = "/rest/v1/fulfillment", produces = MediaType.APPLICATION_JSON_VALUE)
public class FulfillmentController {

    private @Autowired FulfillmentService fulfillmentService;

    @PostMapping(path = "/")
    public ApiResponse fulfillment(@Valid @RequestBody FulfillmentRequest fulfillmentRequest) {
        ApiResponse response = new ApiResponse();
        try {
            Long sellerId = null;
            if (fulfillmentRequest != null && fulfillmentRequest.getSellerId() != null) {
                sellerId = Long.valueOf(fulfillmentRequest.getSellerId());
            } else {
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                response.setMessage("Something went wrong");
            }
            Fulfillment fulfillment=fulfillmentService.addFulfillment(fulfillmentRequest);
            if(Objects.nonNull(fulfillment.getId())){
              response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                System.out.println(ApiResponse.ResponseStatusTypeEnum.SUCCESS.name()+"------");
              response.setMessage("Fulfillment created Successfully");
            }
            else {
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                response.setMessage("Fulfillment is not created");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  response;
    }
}
