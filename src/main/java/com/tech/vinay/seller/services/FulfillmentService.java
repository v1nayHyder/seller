package com.tech.vinay.seller.services;


import com.tech.vinay.pojo.ondc.FulfillmentRequest;
import com.tech.vinay.seller.commons.PersistenceService;
import com.tech.vinay.seller.entities.Fulfillment;
import com.tech.vinay.seller.entities.Seller;
import com.tech.vinay.seller.entities.enums.BaseEntity;
import com.tech.vinay.seller.repositories.FulfillmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FulfillmentService {

    private @Autowired PersistenceService persistenceService;
    private @Autowired FulfillmentRepository fulfillmentRepository;


    public Fulfillment addFulfillment(FulfillmentRequest fulfillmentRequest) {
        Seller seller=persistenceService.findById(Seller.class,fulfillmentRequest.getSellerId());
        if(seller==null){
            throw  new RuntimeException("Seller is not found");
        }
        if(fulfillmentRequest.getType()==null){
            throw  new RuntimeException("Type can't be empty");
        }
        if (fulfillmentRequest.getPhone()==null){
            throw  new RuntimeException("Mobile number is required");
        }
//         boolean isExistsSellerAndType=fulfillmentRepository.isExistsBySellerAndType(fulfillmentRequest.getSellerId(),fulfillmentRequest.getType());
//        if(isExistsSellerAndType){
//            Fulfillment fulfillment=fulfillmentRepository.getBySellerIdAndType(fulfillmentRequest.getSellerId(),fulfillmentRequest.getType());
//            if(fulfillment!=null&&fulfillment.isActive()){
//                throw  new RuntimeException("The requested fulfillment has been already added");
//            }
//
//        }
//        String provider="e-ENAM";
        Fulfillment fulfillment=new Fulfillment();
        fulfillment.setSellerId(fulfillmentRequest.getSellerId());
        fulfillment.setEmail(fulfillmentRequest.getEmail());
        fulfillment.setActive(true);
        fulfillment.setTracking(true);
        fulfillment.setType(fulfillmentRequest.getType());
        fulfillment.setOndcOrgProName(fulfillmentRequest.getOndcOrgProviderName());
        System.out.println(fulfillmentRequest.getOndcOrgProviderName()+"------------------");

        fulfillment.setOndcOrgCategory(fulfillmentRequest.getOndcOrgCategory());
        fulfillment.setOndcOrgTat(fulfillmentRequest.getOndcOrgTat());
        fulfillment.setPhoneNumber(fulfillmentRequest.getPhone());

        fulfillmentRepository.save(fulfillment);
        System.out.println(fulfillment+"+++++++++++++++++++++++++++++++");

        return fulfillment;
    }
}
