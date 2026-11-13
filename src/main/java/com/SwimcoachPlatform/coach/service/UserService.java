package com.SwimcoachPlatform.coach.service;


import com.SwimcoachPlatform.coach.dto.UpdateProfileDTO;
import com.SwimcoachPlatform.coach.dto.UserDTO;
import com.SwimcoachPlatform.coach.dto.UserProfileDTO;
import com.SwimcoachPlatform.coach.entity.Role;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<User> getdAllUsers() {

        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // POST
    public User saveUser(UserDTO userDTO) {

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());

        user.setRole(Role.CLIENT);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public UserProfileDTO getUserProfileByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO profile = new UserProfileDTO();
        profile.setId(user.getId());
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        profile.setEmail(user.getEmail());
        profile.setPhone(user.getPhone());

        return profile;
    }


    public UserProfileDTO updateMyProfile(String email, UpdateProfileDTO dto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."
                ));

        if (dto.getFirstName() == null || dto.getFirstName().isBlank()
                || dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "First name and last name are required."
            );
        }

        user.setFirstName(dto.getFirstName().trim());
        user.setLastName(dto.getLastName().trim());
        user.setPhone(dto.getPhone() == null ? null : dto.getPhone().trim());

        User savedUser = userRepository.save(user);

        UserProfileDTO profile = new UserProfileDTO();
        profile.setId(savedUser.getId());
        profile.setFirstName(savedUser.getFirstName());
        profile.setLastName(savedUser.getLastName());
        profile.setEmail(savedUser.getEmail());
        profile.setPhone(savedUser.getPhone());

        return profile;
    }



}
