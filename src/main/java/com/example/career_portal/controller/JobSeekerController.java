package com.example.career_portal.controller;

import com.example.career_portal.entity.Application;
import com.example.career_portal.entity.JobSeeker;
import com.example.career_portal.service.JobService;
import com.example.career_portal.service.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job-seeker")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;
    private final JobService jobService;

    @GetMapping("/profile")
    public ResponseEntity<JobSeeker> getProfile(Authentication authentication) {
        return ResponseEntity.ok(jobSeekerService.getJobSeekerByEmail(authentication.getName()));
    }

    @PostMapping("/skills")
    public ResponseEntity<JobSeeker> updateSkills(Authentication authentication, @RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(jobSeekerService.updateSkills(authentication.getName(), payload.get("skills")));
    }

    @PutMapping("/account")
    public ResponseEntity<JobSeeker> updateAccount(Authentication authentication, @RequestBody Map<String, String> payload) {
        JobSeeker seeker = jobSeekerService.updateAccount(
                authentication.getName(),
                payload.get("email"),
                payload.get("password")
        );
        return ResponseEntity.ok(seeker);
    }

    @PostMapping("/resume")
    public ResponseEntity<JobSeeker> uploadResume(Authentication authentication, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(jobSeekerService.uploadResume(authentication.getName(), file));
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<Application> apply(Authentication authentication, @PathVariable Long jobId) {
        JobSeeker seeker = jobSeekerService.getJobSeekerByEmail(authentication.getName());
        return ResponseEntity.ok(jobService.applyForJob(seeker, jobId));
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications(Authentication authentication) {
        JobSeeker seeker = jobSeekerService.getJobSeekerByEmail(authentication.getName());
        return ResponseEntity.ok(jobService.getJobSeekerApplications(seeker));
    }
}
