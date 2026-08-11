package com.SwimcoachPlatform.coach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name= "availabilities")
@AllArgsConstructor
@NoArgsConstructor
public class availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private Date dayOfWeek;
    @Getter @Setter
    private Time startTime;
    @Getter @Setter
    private Time endTime;
    @Getter @Setter
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    @Getter @Setter
    private coach coach;


}

