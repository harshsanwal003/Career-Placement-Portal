package com.example.career_portal.repository;

import com.example.career_portal.entity.Company;
import com.example.career_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByUser(User user);
}
