package com.example.career_portal;

import com.example.career_portal.dto.JobDto;
import com.example.career_portal.dto.RegisterRequest;
import com.example.career_portal.entity.Role;
import com.example.career_portal.entity.JobSeeker;
import com.example.career_portal.repository.JobSeekerRepository;
import com.example.career_portal.service.AuthService;
import com.example.career_portal.service.CompanyService;
import com.example.career_portal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AuthService authService;
    private final CompanyService companyService;
    private final JobService jobService;
    private final JobSeekerRepository jobSeekerRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            // Admin
            RegisterRequest adminReq = new RegisterRequest();
            adminReq.setName("System Admin");
            adminReq.setEmail("sanwalharsh00@gmail.com");
            adminReq.setPassword("12345@");
            adminReq.setRole(Role.ADMIN);
            authService.register(adminReq);

            // Companies
            RegisterRequest googleReq = new RegisterRequest();
            googleReq.setName("Google HR");
            googleReq.setEmail("hr@google.com");
            googleReq.setPassword("password");
            googleReq.setRole(Role.COMPANY);
            googleReq.setCompanyName("Google");
            authService.register(googleReq);

            RegisterRequest amazonReq = new RegisterRequest();
            amazonReq.setName("Amazon Recruiter");
            amazonReq.setEmail("recruiting@amazon.com");
            amazonReq.setPassword("password");
            amazonReq.setRole(Role.COMPANY);
            amazonReq.setCompanyName("Amazon");
            authService.register(amazonReq);

            RegisterRequest msReq = new RegisterRequest();
            msReq.setName("Microsoft Talent");
            msReq.setEmail("talent@microsoft.com");
            msReq.setPassword("password");
            msReq.setRole(Role.COMPANY);
            msReq.setCompanyName("Microsoft");
            authService.register(msReq);

            RegisterRequest netflixReq = new RegisterRequest();
            netflixReq.setName("Netflix Talent");
            netflixReq.setEmail("talent@netflix.com");
            netflixReq.setPassword("password");
            netflixReq.setRole(Role.COMPANY);
            netflixReq.setCompanyName("Netflix");
            authService.register(netflixReq);

            // Job Seeker
            RegisterRequest seekerReq = new RegisterRequest();
            seekerReq.setName("harshmain");
            seekerReq.setEmail("student@test.com");
            seekerReq.setPassword("password");
            seekerReq.setRole(Role.JOB_SEEKER);
            authService.register(seekerReq);

            // Set some initial skills for the job seeker
            JobSeeker seeker = jobSeekerRepository.findAll().get(0);
            seeker.setSkills("java, spring, sql, python, aws");
            jobSeekerRepository.save(seeker);

            // Post 10 Jobs
            // Google
            jobService.postJob(companyService.getCompanyByEmail("hr@google.com"), 
                new JobDto("Senior Java Engineer", "Build scalable microservices for Google Search infrastructure.", "java, spring, aws, kubernetes"));
            jobService.postJob(companyService.getCompanyByEmail("hr@google.com"), 
                new JobDto("Frontend React Developer", "Create beautiful UIs for Google Workspace.", "react, javascript, css, html"));
            jobService.postJob(companyService.getCompanyByEmail("hr@google.com"), 
                new JobDto("Cloud Solutions Architect", "Design cloud architectures using GCP and AWS.", "aws, docker, kubernetes, python"));

            // Amazon
            jobService.postJob(companyService.getCompanyByEmail("recruiting@amazon.com"), 
                new JobDto("AWS Backend Developer", "Work on AWS core services using Java and Python.", "java, python, aws, sql"));
            jobService.postJob(companyService.getCompanyByEmail("recruiting@amazon.com"), 
                new JobDto("Full Stack Engineer", "Develop end-to-end features for Amazon Retail.", "java, react, spring, docker"));
            jobService.postJob(companyService.getCompanyByEmail("recruiting@amazon.com"), 
                new JobDto("Data Engineer", "Build ETL pipelines for Amazon logistics.", "python, sql, aws, docker"));

            // Microsoft
            jobService.postJob(companyService.getCompanyByEmail("talent@microsoft.com"), 
                new JobDto("C# .NET Developer", "Join the Azure cloud team.", "c#, sql, docker"));
            jobService.postJob(companyService.getCompanyByEmail("talent@microsoft.com"), 
                new JobDto("Data Scientist", "Analyze big data using Python and SQL.", "python, sql"));

            // Netflix
            jobService.postJob(companyService.getCompanyByEmail("talent@netflix.com"), 
                new JobDto("Video Streaming Engineer", "Optimize video delivery pipelines.", "java, c++, python"));
            jobService.postJob(companyService.getCompanyByEmail("talent@netflix.com"), 
                new JobDto("Node.js Backend Dev", "Build high-throughput APIs for Netflix clients.", "node, javascript, docker"));

            System.out.println("Sample Data Initialized with 10 Jobs!");

        } catch (Exception e) {
            System.out.println("Sample data already exists or failed to initialize: " + e.getMessage());
        }
    }
}

