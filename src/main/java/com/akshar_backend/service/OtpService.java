package com.akshar_backend.service;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    private final Random random = new Random();

    public String generateOtp(int length) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Generate random digits (0-9)
        }
        return otp.toString();
    }
}

