package com.example.career_portal.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
