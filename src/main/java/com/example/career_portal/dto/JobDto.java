package com.example.career_portal.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String title;
    private String description;
    private String requiredSkills;
}
