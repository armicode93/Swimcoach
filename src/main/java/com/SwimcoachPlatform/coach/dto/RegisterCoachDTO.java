package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCoachDTO {

    // Account information
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    // Professional profile
    private String description;
    private String profileImage;
    private String location;
    private int yearsOfExperience;
    private String certifications;
    private String languages;
    private String specializations;
    private String instagram;
    private String facebook;
}