package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.User;
import com.SwimcoachPlatform.coach.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
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
}