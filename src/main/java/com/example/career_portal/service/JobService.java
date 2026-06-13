package com.example.career_portal.service;

import com.example.career_portal.dto.JobDto;
import com.example.career_portal.entity.Application;
import com.example.career_portal.entity.Company;
import com.example.career_portal.entity.Job;
import com.example.career_portal.entity.JobSeeker;
import com.example.career_portal.repository.ApplicationRepository;
import com.example.career_portal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public Job postJob(Company company, JobDto jobDto) {
        Job job = Job.builder()
                .company(company)
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .requiredSkills(jobDto.getRequiredSkills())
                .build();
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByCompany(Company company) {
        return jobRepository.findByCompany(company);
    }

    public Application applyForJob(JobSeeker seeker, Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        
        double score = calculateScore(seeker.getSkills(), job.getRequiredSkills());
        
        Application application = Application.builder()
                .jobSeeker(seeker)
                .job(job)
                .score(score)
                .build();
                
        return applicationRepository.save(application);
    }

    public List<Application> getJobApplications(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        return applicationRepository.findByJobOrderByScoreDesc(job);
    }

    public List<Application> getJobSeekerApplications(JobSeeker seeker) {
        return applicationRepository.findByJobSeeker(seeker);
    }

    private double calculateScore(String seekerSkills, String requiredSkills) {
        if (seekerSkills == null || seekerSkills.isEmpty() || requiredSkills == null || requiredSkills.isEmpty()) {
            return 0.0;
        }

        // Split by comma, semicolon, or whitespace
        Set<String> sSkills = Arrays.stream(seekerSkills.split("[,;\\s]+"))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        Set<String> rSkills = Arrays.stream(requiredSkills.split("[,;\\s]+"))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        if (rSkills.isEmpty()) return 100.0;

        long matched = rSkills.stream().filter(sSkills::contains).count();
        return ((double) matched / rSkills.size()) * 100.0;
    }
}

