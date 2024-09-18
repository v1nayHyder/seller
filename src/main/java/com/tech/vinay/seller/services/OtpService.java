package com.tech.vinay.seller.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpService {

    // Length of the OTP
    private static final int OTP_LENGTH = 6;

    // Characters used to generate the OTP
    private static final String OTP_CHARACTERS = "0123456789";

    // Method to generate the OTP
    public String generateOtp() {
        // Create an instance of SecureRandom to generate random numbers
        SecureRandom random = new SecureRandom();

        // Use StringBuilder to build the OTP
        StringBuilder otp = new StringBuilder();

        // Loop to generate each digit of the OTP
        for (int i = 0; i < OTP_LENGTH; i++) {
            // Get a random index from the OTP_CHARACTERS string
            int randomIndex = random.nextInt(OTP_CHARACTERS.length());

            // Append the character at the random index to the OTP
            otp.append(OTP_CHARACTERS.charAt(randomIndex));
        }

        // Convert StringBuilder to String and return the OTP
        return otp.toString();
    }
}
