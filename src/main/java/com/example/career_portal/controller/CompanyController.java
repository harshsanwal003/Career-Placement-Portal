package com.example.career_portal.controller;

import com.example.career_portal.dto.JobDto;
import com.example.career_portal.entity.Application;
import com.example.career_portal.entity.Company;
import com.example.career_portal.entity.Job;
import com.example.career_portal.service.CompanyService;
import com.example.career_portal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final JobService jobService;

    @GetMapping("/profile")
    public ResponseEntity<Company> getProfile(Authentication authentication) {
        return ResponseEntity.ok(companyService.getCompanyByEmail(authentication.getName()));
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> postJob(Authentication authentication, @RequestBody JobDto jobDto) {
        Company company = companyService.getCompanyByEmail(authentication.getName());
        return ResponseEntity.ok(jobService.postJob(company, jobDto));
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs(Authentication authentication) {
        Company company = companyService.getCompanyByEmail(authentication.getName());
        return ResponseEntity.ok(jobService.getJobsByCompany(company));
    }

    @GetMapping("/jobs/{jobId}/applications")
    public ResponseEntity<List<Application>> getApplications(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.getJobApplications(jobId));
    }
}
