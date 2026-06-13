package com.example.career_portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Your CareerAI Verification Code");
            message.setText("Hello,\n\nYour One-Time Password (OTP) for login is: " + otp + "\n\nThis OTP is valid for 5 minutes.\n\nThank you,\nCareerAI Team");
            mailSender.send(message);
            System.out.println("OTP Email successfully sent to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + to + ". OTP is: " + otp);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
