package com.SwimcoachPlatform.coach.dto;

import com.SwimcoachPlatform.coach.entity.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class BookingDTO {

    private Long userId;
    private Long coachId;
    private Long courseId;

    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private String comment;
    private BookingStatus status;
}