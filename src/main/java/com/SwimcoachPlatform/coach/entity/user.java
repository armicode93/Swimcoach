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
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Getter @Setter
    private String role;

    @Getter @Setter
    private LocalDateTime createdAt;

    @Getter @Setter
    private boolean active;

    /*@OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();
      */

    @OneToMany(mappedBy = "user")
    private List<review> reviews = new ArrayList<>();



}
