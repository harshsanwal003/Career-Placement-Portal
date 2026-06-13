package com.example.career_portal.controller;

import com.example.career_portal.dto.AuthRequest;
import com.example.career_portal.dto.AuthResponse;
import com.example.career_portal.dto.RegisterRequest;
import com.example.career_portal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (request.getRole() == com.example.career_portal.entity.Role.ADMIN) {
            throw new RuntimeException("Admin registration is not allowed.");
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<java.util.Map<String, String>> sendOtp(@RequestBody java.util.Map<String, String> request) {
        authService.sendOtp(request.get("email"));
        return ResponseEntity.ok(java.util.Collections.singletonMap("message", "OTP sent to email."));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody java.util.Map<String, String> request) {
        return ResponseEntity.ok(authService.verifyOtp(request.get("email"), request.get("otp")));
    }
}
