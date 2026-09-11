package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private Long userId;
    private Long coachId;
    private Long bookingId;

    private Integer rating;
    private String comment;
}