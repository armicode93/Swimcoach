package com.SwimcoachPlatform.coach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;

@Entity
@Table(name= "availabilities")
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private DayOfWeek dayOfWeek;
    @Getter @Setter
    private LocalTime startTime;
    @Getter @Setter
    private LocalTime endTime;
    @Getter @Setter
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    @Getter @Setter
    private Coach coach;


    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @Getter @Setter
    private Course course;


}

