package com.example.career_portal.controller;

import com.example.career_portal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(adminService.getSystemStats());
    }

    @GetMapping("/job-seekers")
    public ResponseEntity<Object> getJobSeekers() {
        return ResponseEntity.ok(adminService.getAllJobSeekers());
    }

    @GetMapping("/companies")
    public ResponseEntity<Object> getCompanies() {
        return ResponseEntity.ok(adminService.getAllCompanies());
    }

    @GetMapping("/jobs")
    public ResponseEntity<Object> getJobs() {
        return ResponseEntity.ok(adminService.getAllJobs());
    }
}
