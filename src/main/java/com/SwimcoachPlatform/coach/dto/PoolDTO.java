package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PoolDTO {

    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String description;
    private boolean active;

    private List<Long> coachId;
}