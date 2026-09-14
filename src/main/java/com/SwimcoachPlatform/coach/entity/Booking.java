package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name= "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter
    @Setter
    private LocalDate bookingDate;

    @Getter
    @Setter
    private LocalTime startTime;

    @Getter
    @Setter
    private LocalTime endTime;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Getter
    @Setter
    private LocalDateTime createdAt;


    // Relations

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    @Getter @Setter
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @Getter
    @Setter
    private Course course;



}
