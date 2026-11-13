package com.SwimcoachPlatform.coach.controllers;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import com.SwimcoachPlatform.coach.dto.LoginDTO;
import com.SwimcoachPlatform.coach.dto.LoginResponseDTO;
import com.SwimcoachPlatform.coach.dto.RegisterCoachDTO;
import com.SwimcoachPlatform.coach.dto.RegisterDTO;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public java.util.Map<String, String> register(
            @RequestBody RegisterDTO registerDTO) {

        authService.register(registerDTO);

        return java.util.Map.of(
                "message", "Account created successfully."
        );
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }


@PostMapping("/register-coach")
public ResponseEntity<Map<String, String>> registerCoach(
        @RequestBody RegisterCoachDTO dto) {

    authService.registerCoach(dto);

    return ResponseEntity.ok(
            Map.of("message", "Coach account created successfully.")
    );
}
}
