package com.app.eventOrganizer.controller;


import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUserModel(@RequestBody UserRegistrationDto userDto) {
        UserModel userModel = userService.registerUser(userDto);
        return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }
}
