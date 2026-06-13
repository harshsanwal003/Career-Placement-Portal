package com.example.career_portal.repository;

import com.example.career_portal.entity.Application;
import com.example.career_portal.entity.Job;
import com.example.career_portal.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobOrderByScoreDesc(Job job);
    List<Application> findByJobSeeker(JobSeeker jobSeeker);
}
