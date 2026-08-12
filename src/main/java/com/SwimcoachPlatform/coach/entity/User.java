package com.SwimcoachPlatform.coach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter @Setter
    private Role role;

    @Getter @Setter
    private LocalDateTime createdAt;

    @Getter @Setter
    private boolean active;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private List<Booking> bookings = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private List<Review> reviews = new ArrayList<>();



}
