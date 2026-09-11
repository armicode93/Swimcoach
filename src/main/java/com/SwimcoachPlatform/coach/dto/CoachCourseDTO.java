package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CoachCourseDTO {

    private Long coachId;
    private Long courseId;
    private BigDecimal price;
    private Integer duration;
}