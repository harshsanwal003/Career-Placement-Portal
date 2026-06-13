package com.example.career_portal.dto;

import com.example.career_portal.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String companyName; // Only for COMPANY role
}
