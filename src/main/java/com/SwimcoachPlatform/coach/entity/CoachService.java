package com.SwimcoachPlatform.coach.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "coach_services")
@AllArgsConstructor
@NoArgsConstructor
public class CoachService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private BigDecimal price;

    @Getter @Setter
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    @Getter @Setter
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @Getter @Setter
    private Service service;




}
