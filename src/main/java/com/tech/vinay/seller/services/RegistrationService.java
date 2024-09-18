package com.tech.vinay.seller.services;


import com.tech.vinay.pojo.login.BankDetails;
import com.tech.vinay.pojo.login.PersonalDetails;
import com.tech.vinay.pojo.login.UserRegistrationRequest;

import com.tech.vinay.seller.entities.*;
import com.tech.vinay.seller.repositories.IfscCodeRepository;
import com.tech.vinay.seller.repositories.SellerBankDetailsRepo;
import com.tech.vinay.seller.repositories.SellerRepository;
import com.tech.vinay.seller.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchProviderException;
import java.util.Objects;

@Service
public class RegistrationService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private MasterService masterService;

   @Autowired
   private IfscCodeRepository ifscCodeRepository;
   @Autowired
   private SellerRepository sellerRepository;
   @Autowired
   private SellerBankDetailsRepo sellerBankDetailsRepo;
   private @Autowired PasswordEncoder passwordEncoder;
    public User  registration(UserRegistrationRequest userRegistrationRequest) throws NoSuchProviderException {
        User user=new User();
        Seller seller=new Seller();

        PersonalDetails personalDetails= userRegistrationRequest.getPersonalDetails();
        User existingUser=null;
        if (personalDetails.getEmail()!=null){
            existingUser=userRepository.getByUsername(personalDetails.getEmail());
        }
        else {
            existingUser=userRepository.getByUsername(personalDetails.getMobile());
        }
        System.out.println(existingUser+" "+personalDetails.getEmail()+"+++++++++++++++++++++++"+personalDetails.getMobile()+"==");
        if (existingUser!=null){
            throw  new NoSuchProviderException("User already registered");
        }
        Seller checkMobile=sellerRepository.getByMobile(personalDetails.getMobile());
        if(checkMobile!=null){
            throw  new NoSuchProviderException("Mobile number already registered");
        }
        if(personalDetails.getMobile()!=null){
            user.setUsername(personalDetails.getMobile());
        }
        else{
            user.setUsername(personalDetails.getEmail());
        }
        user.setType("seller");
        user.setDomain(personalDetails.getDomain());
        user.setPassword(passwordEncoder.encode(personalDetails.getPassword()));
        user.setActive(Boolean.TRUE);
        userRepository.save(user);
        seller.setUser(user);
        seller.setName(personalDetails.getName());
        System.out.println(personalDetails.getName()+"============");
        seller.setEmail(personalDetails.getEmail());
        System.out.println(personalDetails.getEmail()+"============");
        seller.setGender(personalDetails.getGender().name());
        System.out.println(personalDetails.getGender()+"============");
        if (personalDetails.getMobile()!=null){
            seller.setMobile(personalDetails.getMobile());
        }
        else {
            throw  new NoSuchProviderException("mobile Number required");
        }
        seller.setDob(personalDetails.getDob());

        seller.setGstNumber(personalDetails.getGstNumber());
        CityMaster cityMaster=masterService.getCityByid(Long.valueOf(personalDetails.getCityId()));
        System.out.println(personalDetails.getCityId()+"ppppppppppppppppppppppppp");
        TehsilMaster tehsilMaster=masterService.getTehsilByid(Long.valueOf(personalDetails.getTehsilId()));
        DistrictMaster districtMaster=masterService.getDistrictByid(Long.valueOf(personalDetails.getDistrictId()));
        StateMaster stateMaster=masterService.getStateByid(Long.valueOf(personalDetails.getStateId()));
        seller.setCityMaster(cityMaster);
        seller.setTehsilMaster(tehsilMaster);
        seller.setDistrictMaster(districtMaster);
        seller.setStateMaster(stateMaster);
        seller.setPost(personalDetails.getPostOffice());
        seller.setPinCode(personalDetails.getPostalCode());
        seller.setPhotoIdType(personalDetails.getPhotoIdType().name());
        seller.setPhotoIdNumber(personalDetails.getPhotoIdNumber());
        seller.setLongDesc(personalDetails.getLongDesc());
        seller.setShortDesc(personalDetails.getShortDesc());
        seller.setActive(Boolean.TRUE);

        seller.setUser(user);
        sellerRepository.save(seller);
        BankDetails bankDetails=userRegistrationRequest.getBankDetails();
        System.out.println("rammmm"+bankDetails.toString());
        if(Objects.nonNull(bankDetails)){
            SellerBankDetails sellerBankDetails=new SellerBankDetails();
            System.out.println(sellerBankDetails +"=====================");
            IfscCodeMaster ifscCodeMaster=ifscCodeRepository.getByIfScCode(bankDetails.getIfscCode());
            System.out.println("heloooooooooo"+ifscCodeMaster+" "+ifscCodeMaster);

            if (ifscCodeMaster!=null){
                sellerBankDetails.setIfscCodeMaster(ifscCodeMaster);
                System.out.println("heloooooooooo"+ifscCodeMaster);
            }
            else {
                throw new RuntimeException("No IFSC details registered on code : " + bankDetails.getIfscCode());
            }
          sellerBankDetails.setSeller(seller);
          sellerBankDetails.setActive(Boolean.TRUE);
         sellerBankDetails.setIfscCode(bankDetails.getIfscCode());
         sellerBankDetails.setAccountNo(bankDetails.getAccountNo());
         sellerBankDetails.setAccountType(bankDetails.getAccountType().name());
         sellerBankDetails.setAccountHolderName(bankDetails.getAccountHolderName());
         sellerBankDetailsRepo.save(sellerBankDetails);
        }
        return user;

    }
}
