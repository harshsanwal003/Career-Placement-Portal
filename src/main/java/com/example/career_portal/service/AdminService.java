package com.example.career_portal.service;

import com.example.career_portal.repository.CompanyRepository;
import com.example.career_portal.repository.JobRepository;
import com.example.career_portal.repository.JobSeekerRepository;
import com.example.career_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public Map<String, Long> getSystemStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalJobSeekers", jobSeekerRepository.count());
        stats.put("totalCompanies", companyRepository.count());
        stats.put("totalJobs", jobRepository.count());
        return stats;
    }

    public Object getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

    public Object getAllCompanies() {
        return companyRepository.findAll();
    }

    public Object getAllJobs() {
        return jobRepository.findAll();
    }
}
