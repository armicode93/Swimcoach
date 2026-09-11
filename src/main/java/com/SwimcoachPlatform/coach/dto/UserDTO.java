package com.SwimcoachPlatform.coach.dto;

import com.SwimcoachPlatform.coach.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastname;
    private String email;
    private Role role;
    private boolean active;
}