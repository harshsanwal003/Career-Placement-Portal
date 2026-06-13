package com.example.career_portal.repository;

import com.example.career_portal.entity.Job;
import com.example.career_portal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompany(Company company);
}
