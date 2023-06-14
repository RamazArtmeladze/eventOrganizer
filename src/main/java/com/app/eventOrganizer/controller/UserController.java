package com.app.eventOrganizer.controller;


import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/userRegistration")
    public UserModel createUserModel(@RequestParam Long ID, @RequestParam String email, @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String userRole, @RequestParam String password,
                                     @RequestParam String passwordConfirmation) {
        return userService.saveToDB(ID, email, firstName, lastName, userRole, password, passwordConfirmation);
    }

    @GetMapping("/user/{email}")
    public UserModel getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/user/{email}")
    public void deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/{email}")
    public UserModel updateUserByEmail(@PathVariable String email, @RequestBody UserModel updatedUser) {
        return userService.updateUserByEmail(email, updatedUser);
    }

}
