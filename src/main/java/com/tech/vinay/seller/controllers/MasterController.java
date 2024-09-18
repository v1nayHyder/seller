package com.tech.vinay.seller.controllers;

import com.tech.vinay.seller.entities.CityMaster;
import com.tech.vinay.seller.entities.DistrictMaster;
import com.tech.vinay.seller.entities.StateMaster;
import com.tech.vinay.seller.entities.TehsilMaster;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping(value = "/api/v1/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/stateList")
    public ApiResponse getAllState() {
        ApiResponse response = new ApiResponse();

        try {
            List<StateMaster> stateMasterList = masterService.getAllState();
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("State List are fetched successfully");
            response.setData(stateMasterList);
            System.out.println(stateMasterList.toString());

        } catch (Exception e) {
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("State Details not found");
        }

        return response;
    }

    @GetMapping("/districtList")
    public ApiResponse getAllDistrict() {
        ApiResponse response = new ApiResponse();
        try {
            List<DistrictMaster> allDistrict = masterService.getAllDistrict();
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("District List are fetched successfully");
            response.setData(allDistrict);

        } catch (Exception e) {
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("District Details not found");
        }
        return response;
    }

    @GetMapping("/tehsilList")
    public ApiResponse getAllTehsil() {
        ApiResponse response = new ApiResponse();
        try {
            List<TehsilMaster> allTehsil = masterService.getAllTehsil();
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("Tehsil List are fetched successfully");
            response.setData(allTehsil);

        } catch (Exception e) {
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("Tehsil Details not found");
        }
        return response;
    }

    @GetMapping("/cityList")
    public ApiResponse getAllCity() {
        ApiResponse response = new ApiResponse();
        try {
            List<CityMaster> cityMasters = masterService.getAllCity();
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("City List are fetched successfully");
            response.setData(cityMasters);

        } catch (Exception e) {
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("City Details not found");
        }
        return response;
    }


//    @GetMapping("/districtList/stateId")
//    public  ApiResponse getAllDistrict(String stateId){
//        ApiResponse response=new ApiResponse();
//        try {
//             response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
//             response.setMessage("Dristrict details fetched successfully");
//             response.setData();
//        }catch (Exception e){
//            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
//            response.setMessage("District details not found");
//        }
//        return  response;
//    }
}
