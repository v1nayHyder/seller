package com.tech.vinay.seller.services;


import com.tech.vinay.seller.entities.*;
import com.tech.vinay.seller.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchProviderException;

import java.util.List;
@Service
public class MasterService {
    @Autowired
    private IfscCodeRepository ifscCodeRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private TehsilRepository tehsilRepository;
    @Autowired
    private CityRepository cityRepository;
    public MasterService( StateRepository stateRepository,
                         DistrictRepository districtRepository, TehsilRepository tehsilRepository, CityRepository cityRepository) {

        this.ifscCodeRepository = ifscCodeRepository;
        this.stateRepository = stateRepository;
        this.districtRepository = districtRepository;
        this.tehsilRepository = tehsilRepository;
        this.cityRepository = cityRepository;
    }

//    public IfscCodeMaster getBankDetailsByIfscCode(BankDetailsByIfscRequest bankDetailsByIfscRequest)
//            throws NoSuchProviderException {
//        IfscCodeMaster ifscCodeMaster = ifscCodeRepository.getByIfScCode(bankDetailsByIfscRequest.getIfscCode());
//        return ifscCodeMaster;
//    }

    public StateMaster getStateByid(Long stateId) throws NoSuchProviderException {
        StateMaster stateMaster = stateRepository.getByStateId(stateId);
        return stateMaster;
    }
    public List<StateMaster> getAllState() throws NoSuchProviderException {
        return stateRepository.findAll();
    }
    public List<DistrictMaster> getAllDistrict() throws NoSuchProviderException {
        return districtRepository.findAll();
    }
    public List<TehsilMaster> getAllTehsil() throws NoSuchProviderException {
        return tehsilRepository.findAll();
    }
    public List<CityMaster> getAllCity() throws NoSuchProviderException {
        return cityRepository.findAll();
    }

    public DistrictMaster getDistrictByid(Long district_id) throws NoSuchProviderException {
        DistrictMaster districtMaster = districtRepository.getByDistrictId(district_id);
        return districtMaster;
    }
    @Transactional
    public TehsilMaster getTehsilByid(Long id) throws NoSuchProviderException {
        TehsilMaster tehsilMaster = tehsilRepository.getByTehsilId(id);
        return tehsilMaster;
    }

    public CityMaster getCityByid(Long id) throws NoSuchProviderException {
        System.out.println("9999999999999999999999999  "+id);

        CityMaster cityMaster = cityRepository.getByCityId(id);

        System.out.println(cityMaster.getId()+"9999999999999999999999999"+cityMaster.toString());
        return cityMaster;
    }


}