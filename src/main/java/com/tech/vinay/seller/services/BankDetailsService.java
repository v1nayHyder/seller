package com.tech.vinay.seller.services;


import com.tech.vinay.pojo.login.SellerBankDetailsRequest;
import com.tech.vinay.pojo.login.SellerBankDetailsResponse;
import com.tech.vinay.seller.commons.PersistenceService;
import com.tech.vinay.seller.entities.IfscCodeMaster;
import com.tech.vinay.seller.entities.Seller;
import com.tech.vinay.seller.entities.SellerBankDetails;
import com.tech.vinay.seller.repositories.IfscCodeRepository;
import com.tech.vinay.seller.repositories.SellerBankDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service
public class BankDetailsService {

    @Autowired
    private IfscCodeRepository ifscCodeRepository;
    private @Autowired PersistenceService persistenceService;

    private @Autowired SellerBankDetailsRepo sellerBankDetailsRepo;
    public SellerBankDetails createBankDetails(SellerBankDetailsRequest sellerBankDetailsRequest) {
        SellerBankDetails sellerBankDetails = new SellerBankDetails();
        Seller seller = persistenceService.findById(
                Seller.class, sellerBankDetailsRequest.getSellerId());
        if (seller == null) {
            throw new RuntimeException("Seller with ID " + sellerBankDetailsRequest.getSellerId() + " not found.");
        }
        sellerBankDetails.setSeller(seller);
        if(sellerBankDetailsRequest.getAccountHolderName() == null){
            throw new RuntimeException("Please provide valid account holder name");
        }
        sellerBankDetails.setActive(Boolean.TRUE);
        sellerBankDetails.setAccountHolderName(sellerBankDetailsRequest.getAccountHolderName());
        if(sellerBankDetailsRequest.getAccountNumber() == null){
            throw new RuntimeException("Please provide valid account number");
        }
        sellerBankDetails.setAccountNo(sellerBankDetailsRequest.getAccountNumber());
        sellerBankDetails.setIfscCode(sellerBankDetailsRequest.getIfscCode());
        IfscCodeMaster ifscCodeMaster = ifscCodeRepository.getByIfScCode(
                sellerBankDetailsRequest.getIfscCode()
        );
        sellerBankDetails.setAccountType(sellerBankDetailsRequest.getAccountType().name());

        if (ifscCodeMaster != null) {
            sellerBankDetails.setIfscCodeMaster(ifscCodeMaster);
        }
        else{
            LOGGER.warn("No IFSC details registered on code : " + sellerBankDetailsRequest.getIfscCode());
        }
        System.out.println(sellerBankDetails

        );
        persistenceService.save(sellerBankDetails);
        return sellerBankDetails;

    }

    public SellerBankDetails deleteBankDetails(Integer sellerBankDetailsId) {
        if (sellerBankDetailsId == null) {
            throw new RuntimeException("Please enter valid seller BankId");
        }

       SellerBankDetails sellerBankDetails=persistenceService.findById(SellerBankDetails.class,sellerBankDetailsId);
       if(sellerBankDetails==null){
           throw  new RuntimeException("Seller bank details with ID " + sellerBankDetailsId + " not found.");
      }
       sellerBankDetails.setActive(Boolean.FALSE);
       persistenceService.delete(sellerBankDetails);
       return sellerBankDetails;
    }

    public SellerBankDetails updateBankDetails(SellerBankDetailsRequest sellerBankDetailsRequest) {
        if(sellerBankDetailsRequest.getId()==null) {
            throw  new RuntimeException("Please enter valid bank details ID");
        }
        SellerBankDetails sellerBankDetails=persistenceService.findById(SellerBankDetails.class,sellerBankDetailsRequest.getId());
        if(sellerBankDetails==null){
            throw  new RuntimeException("Seller bank details with ID "+sellerBankDetailsRequest.getId()+" not found!");

        }
        if(sellerBankDetailsRequest.getAccountHolderName()!=null){
            sellerBankDetails.setAccountHolderName(sellerBankDetailsRequest.getAccountHolderName());
        }
        if(sellerBankDetailsRequest.getAccountNumber()!=null){
            sellerBankDetails.setAccountNo(sellerBankDetailsRequest.getAccountNumber());
        }
        if (sellerBankDetailsRequest.getIfscCode()!=null){
            IfscCodeMaster ifscCodeMaster=ifscCodeRepository.getByIfScCode(sellerBankDetailsRequest.getIfscCode());
            if (ifscCodeMaster!=null){
                sellerBankDetails.setIfscCode(sellerBankDetailsRequest.getIfscCode());
            }
            else {
                throw  new RuntimeException("Bank Details with Ifsc Code "+sellerBankDetailsRequest.getIfscCode()+" not found !");
            }
        }
        if (sellerBankDetailsRequest.getAccountType()!=null){
            sellerBankDetails.setAccountType(sellerBankDetailsRequest.getAccountType().name());
        }
        SellerBankDetails sellerBankDetails1=sellerBankDetailsRepo.save(sellerBankDetails);
        return sellerBankDetails1;
    }

    public SellerBankDetailsResponse getBankDetails(Integer sellerBankDetailsId) {
        if(sellerBankDetailsId==null){
            throw new RuntimeException("Please enter valid Seller bank details ID !");
        }
        SellerBankDetails sellerBankDetails=persistenceService.findById(SellerBankDetails.class,sellerBankDetailsId);
        if (sellerBankDetails==null&& !sellerBankDetails.isActive()){
            throw  new RuntimeException("Seller Bank Details with ID "+sellerBankDetailsId+" not found");
        }
        SellerBankDetailsResponse sellerBankDetailsResponse=new SellerBankDetailsResponse();
        sellerBankDetailsResponse.setId(sellerBankDetails.getId());
        sellerBankDetailsResponse.setAccountHolderName(sellerBankDetails.getAccountHolderName());
        sellerBankDetailsResponse.setAccountNumber(sellerBankDetails.getAccountNo());
        sellerBankDetailsResponse.setIfscCode(sellerBankDetails.getIfscCode());
        sellerBankDetailsResponse.setAccountType(sellerBankDetails.getAccountType());
//        sellerBankDetailsResponse.setSellerId(sellerBankDetails.getSeller()!=null?sellerBankDetails.getId().intValue());
        sellerBankDetailsResponse.setSellerId(sellerBankDetails.getSeller().getId().intValue());
        return sellerBankDetailsResponse;
    }
}
