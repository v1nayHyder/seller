package com.tech.vinay.seller.services;


import com.tech.vinay.seller.entities.OndcCategoryMaster;
import com.tech.vinay.seller.models.OndcCategoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OndcCategoryMasterService {

    private final OndcCategoryMasterRepository ondcCategoryMasterRepository;

    @Autowired
    public OndcCategoryMasterService(OndcCategoryMasterRepository ondcCategoryMasterRepository){
        this.ondcCategoryMasterRepository=ondcCategoryMasterRepository;
    }
    public OndcCategoryMaster createCategory(OndcCategoryMaster ondcCategoryMaster){
        System.out.println(ondcCategoryMaster);
        return ondcCategoryMasterRepository.save(ondcCategoryMaster);
    }
}
