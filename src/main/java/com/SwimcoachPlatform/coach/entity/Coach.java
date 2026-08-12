package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "coaches")
@AllArgsConstructor
@NoArgsConstructor
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String firstName;
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

    // Relations
    @OneToMany(mappedBy = "coach")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "coach")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "coach_pool",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "pool_id")
    )
    private List<Pool> pools = new ArrayList<>();

    @OneToMany(mappedBy = "coach")
    private List<CoachService> coachServices = new ArrayList<>();




}
