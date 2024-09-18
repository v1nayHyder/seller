package com.tech.vinay.seller.services;

import com.tech.vinay.pojo.login.LoginRequest;
import com.tech.vinay.seller.entities.Seller;
import com.tech.vinay.seller.entities.User;
import com.tech.vinay.seller.entities.UserOtpData;
import com.tech.vinay.seller.models.ApiResponse;
import com.tech.vinay.seller.repositories.OptDataRepository;
import com.tech.vinay.seller.repositories.SellerRepository;
import com.tech.vinay.seller.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    private final OptDataRepository optDataRepository;
//    @Autowired
//    public LoginService(UserRepository userRepository, SellerRepository sellerRepository, PasswordEncoder passwordEncoder,OptDataRepository optDataRepository) {
//        this.userRepository = userRepository;
//        this.sellerRepository = sellerRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.optDataRepository=optDataRepository;
//    }

    public ApiResponse
    loginByUsernameOrMobileNumber(LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String rawPassword = loginRequest.getPassword();
        ApiResponse response = new ApiResponse();

//        logger.info("Login attempt for user ID: {}", userId);
        User user = userRepository.getByUsername(userId);
        Seller seller = null;

        if (user == null) {
            seller = sellerRepository.getByEmail(userId);
        }
        if (user == null && seller == null) {
//            logger.debug("No user or seller found with user ID: {}", userId);
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            response.setMessage("Username/mobile number is incorrect");
            return response;
        }
        if(loginRequest.getPassword()!=null)
        {
        String password=userRepository.findPassword(userId);
        if ( passwordEncoder.matches(rawPassword,password)) {
//            logger.info("Successful login for user ID: {}", userId);
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("You have logged in successfully");
        } else {
//            logger.warn("Incorrect password attempt for user ID: {}", userId);
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
            response.setMessage("Password incorrect");
        }
        }
        else {
            UserOtpData userOtpData = optDataRepository.verifyOtp(loginRequest.getUserId());
            if (Objects.nonNull(userOtpData.getOtp())&&userOtpData.getOtp().equals(loginRequest.getOtp())) {
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                // Subtract 1 minute from currentTimestamp
                Timestamp currentTimestampMinusOneMinute = new Timestamp(currentTimestamp.getTime() - 60 * 1000);

                if(userOtpData.getExpirationTime().after(currentTimestampMinusOneMinute)) {
                    // logger.info("Successful login for user ID: {}", userId);

                    response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                    response.setMessage("You have logged in successfully");
                }
                else{
                    response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                    response.setMessage("Your OTP has been Expired");
                  }
            } else {
//                logger.warn("Incorrect OTP attempt for user ID: {}", userId);
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
                response.setMessage("Please enter valid OTP.");            }
        }
        return response;
    }

    public ApiResponse resetPassword(String username, String password) {
        ApiResponse response = new ApiResponse();
        User user = userRepository.getByUsername(username);
        if (Objects.isNull(user)) {
            Seller seller = sellerRepository.getByMobile(username);
            if (Objects.isNull(seller)) {
                response.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
                response.setMessage("Please enter valid username");
                return  response;
            }
        }
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            response.setMessage("Your password has been updated");

        return response;
    }

}



//package com.tech.vinay.seller.services;
//
//import com.tech.vinay.pojo.login.LoginRequest;
//import com.tech.vinay.seller.entities.Seller;
//import com.tech.vinay.seller.entities.User;
//import com.tech.vinay.seller.models.ApiResponse;
//import com.tech.vinay.seller.repositories.SellerRepository;
//import com.tech.vinay.seller.repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//
//    private final UserRepository userRepository;
//    private final SellerRepository sellerRepository;
//    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
//
//    @Autowired
//    public LoginService(UserRepository userRepository, SellerRepository sellerRepository) {
//        this.userRepository = userRepository;
//        this.sellerRepository = sellerRepository;
//    }
//
//    public ApiResponse loginByUsernameOrMobileNumber(LoginRequest loginRequest) {
//        ApiResponse response = new ApiResponse();
//
//        User user = userRepository.getByUsername(loginRequest.getUserId());
//        Seller seller = null;
//
//        if (user == null) {
//            seller = sellerRepository.getByEmail(loginRequest.getUserId());
//        }
//
//        logger.info("Login attempt for user ID: {}", loginRequest.getUserId());
//        String password=userRepository.findPassword(loginRequest.getPassword());
//        if (user == null && seller == null) {
//            logger.debug("No user or seller found with user ID: {}", loginRequest.getUserId());
//            response.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
//            response.setMessage("Username/mobile number is incorrect");
//        }
//        else if(password==loginRequest.getPassword()) {
//            logger.info("Successful login for user ID: {}", loginRequest.getUserId());
//            response.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
//            response.setMessage("You have logged in successfully");
//        }
//        else {
//            logger.warn("Incorrect password attempt for user ID: {}", loginRequest.getUserId());
//            response.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
//            response.setMessage("password incorrect");
//        }
//
//        return response;
//    }
//}
