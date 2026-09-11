package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDTO {

    private String firstName;
    private String lastname;
    private String email;
    private String phone;

    private String description;
    private String profileImage;
    private String location;
    private int yearsOfExperience;

    private String certifications;
    private String languages;
    private String specializations;

    private String instagram;
    private String facebook;

    private boolean active;
}