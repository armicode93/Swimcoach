package com.SwimcoachPlatform.coach.service;

import com.SwimcoachPlatform.coach.dto.RegisterDTO;
import com.SwimcoachPlatform.coach.entity.Role;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterDTO dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        // Password hashée avec BCrypt
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Valeurs gérées par le backend
        user.setRole(Role.CLIENT);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}