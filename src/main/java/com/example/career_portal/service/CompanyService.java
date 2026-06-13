package com.example.career_portal.service;

import com.example.career_portal.entity.Company;
import com.example.career_portal.entity.User;
import com.example.career_portal.repository.CompanyRepository;
import com.example.career_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public Company getCompanyByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return companyRepository.findByUser(user).orElseThrow();
    }
}
