package com.SwimcoachPlatform.coach.dto;

import com.SwimcoachPlatform.coach.entity.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AvailabilityDTO {

    private Long coachId;
    private Long courseId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean active;
}