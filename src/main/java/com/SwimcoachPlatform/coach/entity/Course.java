package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "courses")
@AllArgsConstructor
@NoArgsConstructor

// rappresenta il tipo di corso/attivita disponibile sulla piattaforma
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private boolean active;


    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;


    @OneToMany(mappedBy = "course")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CoachCourse> coachCourses = new ArrayList<>();





}
