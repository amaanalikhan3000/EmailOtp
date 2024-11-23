package com.Email.demo.Service;

import com.Email.demo.Entity.OtpDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpStorageService {
    private Map<String, OtpDetails> otpStore = new HashMap<>();

    public void storeOtp(String email, String otp) {
        OtpDetails otpDetails = new OtpDetails(email, otp, LocalDateTime.now().plusMinutes(5));
        otpStore.put(email, otpDetails);
    }

    public OtpDetails getOtpDetails(String email) {
        return otpStore.get(email);
    }

    public void removeOtp(String email) {
        otpStore.remove(email);
    }
}
