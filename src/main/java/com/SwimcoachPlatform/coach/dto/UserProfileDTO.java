
package com.SwimcoachPlatform.coach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
