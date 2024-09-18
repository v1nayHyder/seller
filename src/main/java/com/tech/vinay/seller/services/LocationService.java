package com.tech.vinay.seller.services;

import com.tech.vinay.pojo.ondc.location.SellerLocationRequest;
import com.tech.vinay.seller.commons.PersistenceService;
import com.tech.vinay.seller.entities.Seller;
import com.tech.vinay.seller.entities.SellerLocation;
import com.tech.vinay.seller.repositories.SellerLocationRepository;
import com.tech.vinay.seller.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {


     private @Autowired PersistenceService persistenceService;
     private @Autowired SellerLocationRepository sellerLocationRepository;

    public SellerLocation createSellerLocation(SellerLocationRequest sellerLocationRequest) {
        SellerLocation sellerLocation=new SellerLocation();
        sellerLocation.setDays(sellerLocation.getDays());
        sellerLocation.setActive(Boolean.TRUE);
        sellerLocation.setClosed(Boolean.FALSE);
        sellerLocation.setAreaCode(sellerLocationRequest.getAreaCode());
        Seller seller=persistenceService.findById(Seller.class,sellerLocationRequest.getSellerId());
        if(seller==null){
            throw new RuntimeException("Seller is not found");
        }
        sellerLocation.setSeller(seller);
        sellerLocation.setAreaCode(sellerLocationRequest.getAreaCode());
//        sellerLocation.setStdCode(getStdCodeFromPincode(sellerLocationRequest.getAreaCode()));

        sellerLocation.setState(sellerLocationRequest.getState());
        Time startTime = sellerLocationRequest.getStartTime();
        sellerLocation.setName(sellerLocationRequest.getName());
        sellerLocation.setLocality(sellerLocationRequest.getLocality());
        sellerLocation.setAreaCode(sellerLocationRequest.getAreaCode());
        sellerLocation.setDistrict(sellerLocationRequest.getDistrict());
        sellerLocation.setGps(sellerLocationRequest.getGps());
        sellerLocation.setState(sellerLocationRequest.getState());
        sellerLocation.setTehsil(sellerLocationRequest.getTensile());
        sellerLocation.setStreet(sellerLocationRequest.getStreet());

        if(startTime == null) {
            startTime = new Time(0, 0, 0);
        }
        Time endTime = sellerLocationRequest.getEndTime();
        if(endTime == null) {
            endTime = new Time(23, 59, 0);
        }
        sellerLocation.setStateTime(startTime);
        sellerLocation.setEndTime(endTime);
        sellerLocation.setConsumerCareContactName(sellerLocationRequest.getConsumerCareContactName());
        sellerLocation.setConsumerCareContactEmail(sellerLocationRequest.getConsumerCareEmail());
        sellerLocation.setConsumerCarePhone(sellerLocationRequest.getConsumerCarePhone());
        sellerLocation.setDays(sellerLocationRequest.getDays());
//        GeoLocation geoLocation = geoLocationService.get(sellerLocationRequest.getAreaCode());
//        if(geoLocation != null) {
//            sellerLocation.setGps(String.join(", ", geoLocation.getLatitude(), geoLocation.getLongitude()));
//        }
        persistenceService.save(sellerLocation);
//        createSellerLocationHolidays(
//                sellerLocationRequest.getHolidayDates().toArray(new LocalDate[0]), sellerLocation
//        );
        return sellerLocation;

    }

    public List<SellerLocation> getAllLocationBySellerId(Integer sellerId) {
        List<SellerLocation> sellerLocations = sellerLocationRepository.findBySellerId(sellerId);
        if (sellerLocations == null || sellerLocations.isEmpty()) {
            throw new RuntimeException("No locations found for the given Seller Id");
        }
        return sellerLocations;
    }
}
