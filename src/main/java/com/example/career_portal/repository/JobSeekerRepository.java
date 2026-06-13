package com.example.career_portal.repository;

import com.example.career_portal.entity.JobSeeker;
import com.example.career_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByUser(User user);
}
