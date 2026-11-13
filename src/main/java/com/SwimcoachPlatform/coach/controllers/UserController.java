package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.dto.UpdateProfileDTO;
import com.SwimcoachPlatform.coach.dto.UserDTO;
import com.SwimcoachPlatform.coach.dto.UserProfileDTO;
import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET: recupera tutti gli utenti
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getdAllUsers();
    }

    // GET: recupera un utente tramite ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // POST: crea un nuovo utente

    @PostMapping
    public User saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
    // PUT: modifica un utente
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(id, user);
    }

    // DELETE: elimina un utente
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/me")
    public UserProfileDTO getMyProfile(Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You must be logged in to view your profile."
            );
        }

        String email = authentication.getName();

        return userService.getUserProfileByEmail(email);
    }

    @PutMapping("/me")
    public UserProfileDTO updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateProfileDTO dto) {

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You must be logged in to update your profile."
            );
        }

        String email = authentication.getName();

        return userService.updateMyProfile(email, dto);
    }

}