package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Availability;
import com.SwimcoachPlatform.coach.service.AvailabilityService;

public class AvailabilityController {

    private final AvailabilityService availabilityService;
    public AvailabilityController( AvailabilityService availabilityService)
    {

        this.availabilityService = availabilityService;
    }

}
