package com.Email.demo.Controller;

import com.Email.demo.Entity.OtpDetails;
import com.Email.demo.Service.EmailOtpService;
import com.Email.demo.Service.OtpStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private EmailOtpService emailOtpService;

    @Autowired
    private OtpStorageService otpStorageService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {
        emailOtpService.sendOtp(email);
        return "OTP sent to " + email;
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        OtpDetails otpDetails = otpStorageService.getOtpDetails(email);

        if (otpDetails == null) {
            return "No OTP found for this email.";
        }

        if (otpDetails.getExpirationTime().isBefore(LocalDateTime.now())) {
            otpStorageService.removeOtp(email);
            return "OTP has expired.";
        }

        if (!otpDetails.getOtp().equals(otp)) {
            return "Invalid OTP.";
        }

        otpStorageService.removeOtp(email);
        return "OTP verified successfully!";
    }
}