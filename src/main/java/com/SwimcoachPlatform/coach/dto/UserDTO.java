package com.SwimcoachPlatform.coach.dto;

import com.SwimcoachPlatform.coach.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    private boolean active;
}