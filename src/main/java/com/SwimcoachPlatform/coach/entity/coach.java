package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "coaches")
@AllArgsConstructor
@NoArgsConstructor
public class coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phone;

    // Professional profile
    @Column(length=2000)
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String profileImage;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private int yearsOfExperience;

    @Column(length = 1000)
    @Getter @Setter
    private String certifications;

    // Languages spoken by the coach
    @Column(length = 500)
    @Getter @Setter
    private String languages;


    // Specializations
    @Column(length = 1000)
    @Getter @Setter
    private String specializations;

    // Social media / website
    @Getter @Setter
    private String instagram;

    @Getter @Setter
    private String facebook;


    // Account status
    @Getter @Setter
    private boolean active;



}
