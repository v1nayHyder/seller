package com.tech.vinay.seller.services;

import com.tech.vinay.pojo.login.GenerateOtpRequest;
import com.tech.vinay.seller.commons.PersistenceService;
import com.tech.vinay.seller.entities.Seller;
import com.tech.vinay.seller.entities.User;
import com.tech.vinay.seller.entities.UserOtpData;
import com.tech.vinay.seller.entities.enums.BaseEntity;
import com.tech.vinay.seller.repositories.OptDataRepository;
import com.tech.vinay.seller.repositories.SellerRepository;
import com.tech.vinay.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OtpDataService  {

    private @Autowired PersistenceService persistenceService;
    private @Autowired OptDataRepository optDataRepository;

    private @Autowired OtpService otpService;
    @Value("5")
    private Integer smsLimit;
    @Value("60")
    private Integer otpTimer;
    @Value("60")
    private Integer smsBlock;
    @Value("150")
    private Integer expirationTime;
    @Value("true")
    private boolean skipSMS;

    private @Autowired UserRepository userRepository;
    private @Autowired SellerRepository sellerRepository;

    public void verifyIfMobileNumberAlreadyRegistered(GenerateOtpRequest generateOtpRequest) {
        User existingUser=userRepository.getByUsername(generateOtpRequest.getPhoneNumber());
        if (existingUser!=null){
            throw  new RuntimeException("Mobile number entered is already registered");
        }
        Seller existingSeller=sellerRepository.getByMobile(generateOtpRequest.getPhoneNumber());
        if (existingSeller!=null){
            throw  new RuntimeException("Mobile number entered is already registered");
        }
    }

    public UserOtpData generateOtpAndSave(GenerateOtpRequest otpRequest, String generalOtpOndc) {
        UserOtpData userOtpData=new UserOtpData();
        System.out.println(otpRequest.getUserLoginId()+"  "+otpRequest.getEmail()+" "+otpRequest.getPhoneNumber()+"-----------------------------");
      try {

          if(otpRequest==null)
              throw  new RuntimeException("Please provide valid request");

          if(otpRequest!=null&&otpRequest.getPhoneNumber()==null&&otpRequest.getEmail()==null)
              throw new RuntimeException("Please provide email or mobile no");

          String otp;
          List<UserOtpData> otpDataList=optDataRepository.getTimeByMobile(otpRequest.getPhoneNumber(),smsLimit);
          if(otpDataList!=null&&!otpDataList.isEmpty()){
              LocalDateTime now=LocalDateTime.now();
              LocalDateTime lastOtpCreatedAt= otpDataList.get(0).getCreatedAt();
              LocalDateTime firstOtpCreatedAt=otpDataList.get(otpDataList.size()-1).getCreatedAt();
              Duration durationBlock=Duration.between(firstOtpCreatedAt,now);
              long blockTime=durationBlock.toMinutes();
              Duration durationLimit=Duration.between(lastOtpCreatedAt,now);
              long elapseTime=durationLimit.getSeconds();
              if (otpDataList.size()==smsLimit&& blockTime<smsBlock)
                  throw  new RuntimeException("You've exceeded maximum OTP requests. Please try again after some time");

              if(elapseTime>=otpTimer)
                  otp = otpService.generateOtp();
              else
                  throw new RuntimeException("Please wait before requesting another OTP.");

              }
          else {
              otp = otpService.generateOtp();
              }

          userOtpData.setOtpDataId(1);
          userOtpData.setOtp(otp);
          if(otpRequest.getPhoneNumber()!=null){
              userOtpData.setPhoneNumber(otpRequest.getPhoneNumber());
          }
          userOtpData.setValidated(Boolean.FALSE);
          if (otpRequest.getEmail()!=null){
              userOtpData.setEmail(otpRequest.getEmail());
          }
          SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Calendar calendar=Calendar.getInstance();
          if (expirationTime!=null){
             calendar.add(Calendar.SECOND,expirationTime);
          }else {
              calendar.add(Calendar.SECOND,30);
          }
          Date date=calendar.getTime();
          userOtpData.setExpirationTime(Timestamp.from(calendar.toInstant()));
//          userOtpData.setCreatedAt(LocalDateTime.now());
//          userOtpData.setUpdatedAt(LocalDateTime.now());


      }

      catch (Exception e){
          System.out.println(e.getMessage());
      }
        System.out.println(userOtpData+"+++++++++++++++++++++++++++++");
      UserOtpData updateUserOtp=optDataRepository.save(userOtpData);

     return updateUserOtp;
    }
}
