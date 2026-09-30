package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.dto.LoginDTO;
import com.SwimcoachPlatform.coach.dto.LoginResponseDTO;
import com.SwimcoachPlatform.coach.dto.RegisterDTO;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }
}
