package com.example.career_portal.service;

import com.example.career_portal.entity.JobSeeker;
import com.example.career_portal.entity.User;
import com.example.career_portal.repository.JobSeekerRepository;
import com.example.career_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final String UPLOAD_DIR = "uploads/";

    public JobSeeker getJobSeekerByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return jobSeekerRepository.findByUser(user).orElseThrow();
    }

    public JobSeeker updateSkills(String email, String skills) {
        JobSeeker seeker = getJobSeekerByEmail(email);
        seeker.setSkills(skills);
        return jobSeekerRepository.save(seeker);
    }

    public JobSeeker updateAccount(String currentEmail, String newEmail, String newPassword) {
        JobSeeker seeker = getJobSeekerByEmail(currentEmail);
        User user = seeker.getUser();
        
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            user.setEmail(newEmail.trim());
        }
        
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        
        userRepository.save(user);
        return seeker;
    }

    public JobSeeker uploadResume(String email, MultipartFile file) throws IOException {
        JobSeeker seeker = getJobSeekerByEmail(email);
        
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());
        
        seeker.setResumePath(filePath.toString());
        
        String content = "";
        if (file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            try (PDDocument document = Loader.loadPDF(file.getBytes())) {
                PDFTextStripper stripper = new PDFTextStripper();
                content = stripper.getText(document).toLowerCase();
            }
        } else {
            content = new String(file.getBytes()).toLowerCase();
        }

        // Skill Extraction
        List<String> knownSkills = Arrays.asList("java", "python", "spring", "react", "sql", "javascript", "html", "css", "docker", "aws", "kubernetes", "angular", "node", "express", "c++", "c#", "php", "ruby", "go", "swift", "kotlin");
        String extractedSkills = knownSkills.stream()
                .filter(content::contains)
                .collect(Collectors.joining(","));
        
        if (!extractedSkills.isEmpty()) {
            String currentSkills = seeker.getSkills();
            if (currentSkills == null || currentSkills.isEmpty()) {
                seeker.setSkills(extractedSkills);
            } else {
                List<String> allSkills = Arrays.asList((currentSkills + "," + extractedSkills).split(","));
                String uniqueSkills = allSkills.stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .distinct()
                        .collect(Collectors.joining(","));
                seeker.setSkills(uniqueSkills);
            }
        }

        // Generate Resume Insights
        seeker.setResumeInsights(generateInsights(content, extractedSkills));
        
        return jobSeekerRepository.save(seeker);
    }

    private String generateInsights(String content, String extractedSkills) {
        List<String> insights = new ArrayList<>();
        
        // Language Insight
        List<String> languages = Arrays.asList("java", "python", "javascript", "c++", "c#", "php", "ruby", "go", "swift", "kotlin");
        List<String> foundLangs = languages.stream().filter(content::contains).collect(Collectors.toList());
        if (!foundLangs.isEmpty()) {
            insights.add("Main Languages: " + String.join(", ", foundLangs));
        }

        // Focus Area Insight
        if (content.contains("backend") || content.contains("spring") || content.contains("sql") || content.contains("api")) {
            insights.add("Primary Focus: Backend Development");
        } else if (content.contains("frontend") || content.contains("react") || content.contains("angular") || content.contains("css")) {
            insights.add("Primary Focus: Frontend Development");
        } else if (content.contains("fullstack") || content.contains("full-stack")) {
            insights.add("Primary Focus: Full-Stack Development");
        }

        // Experience Insight (Very basic check)
        if (content.contains("senior") || content.contains("lead") || content.contains("manager") || content.contains("years of experience")) {
            insights.add("Experience Level: Experienced/Senior");
        } else {
            insights.add("Experience Level: Entry/Junior");
        }

        if (insights.isEmpty()) {
            return "No clear insights detected. Try updating your resume with more keywords.";
        }
        
        return String.join(" | ", insights);
    }
}
