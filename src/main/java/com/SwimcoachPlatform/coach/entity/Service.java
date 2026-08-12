package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "services")
@AllArgsConstructor
@NoArgsConstructor

// rappresenta il tipo di corso/attivita disponibile sulla piattaforma
public class Service {

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


    @OneToMany(mappedBy = "service")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "service")
    private List<CoachService> coachServices = new ArrayList<>();





}
