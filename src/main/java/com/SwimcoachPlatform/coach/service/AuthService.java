package com.SwimcoachPlatform.coach.service;
import com.SwimcoachPlatform.coach.dto.RegisterCoachDTO;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.repository.CoachRepository;

import com.SwimcoachPlatform.coach.dto.LoginDTO;
import com.SwimcoachPlatform.coach.dto.LoginResponseDTO;
import com.SwimcoachPlatform.coach.dto.RegisterDTO;
import com.SwimcoachPlatform.coach.entity.Coach;
import com.SwimcoachPlatform.coach.entity.Role;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.CoachRepository;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CoachRepository coachRepository;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService, CoachRepository coachRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.coachRepository = coachRepository;
    }


    public User register(RegisterDTO registerDTO) {

        String email = registerDTO.getEmail().trim().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.CONFLICT,
                    "An account with this email already exists."
            );
        }

        User user = new User();

        user.setFirstName(registerDTO.getFirstName().trim());
        user.setLastName(registerDTO.getLastName().trim());
        user.setEmail(email);
        user.setPhone(registerDTO.getPhone());

        user.setPassword(
                passwordEncoder.encode(registerDTO.getPassword())
        );

        user.setRole(Role.CLIENT);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User registerCoach(RegisterCoachDTO dto) {

        String email = dto.getEmail().trim().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.CONFLICT,
                    "An account with this email already exists."
            );
        }

        // Create User account
        User user = new User();

        user.setFirstName(dto.getFirstName().trim());
        user.setLastName(dto.getLastName().trim());
        user.setEmail(email);
        user.setPhone(dto.getPhone());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole(Role.COACH);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        // Create Coach profile
        Coach coach = new Coach();

        coach.setFirstName(dto.getFirstName().trim());
        coach.setLastname(dto.getLastName().trim());
        coach.setEmail(email);
        coach.setPhone(dto.getPhone());

        coach.setDescription(dto.getDescription());
        coach.setProfileImage(dto.getProfileImage());
        coach.setLocation(dto.getLocation());
        coach.setYearsOfExperience(dto.getYearsOfExperience());
        coach.setCertifications(dto.getCertifications());
        coach.setLanguages(dto.getLanguages());
        coach.setSpecializations(dto.getSpecializations());
        coach.setInstagram(dto.getInstagram());
        coach.setFacebook(dto.getFacebook());

        coach.setActive(true);
        coach.setUser(user);

        coachRepository.save(coach);

        return user;
    }



    public LoginResponseDTO login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Email or password incorrect"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email or password incorrect");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
}