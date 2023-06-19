package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {
    public UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel registerUser(UserRegistrationDto userDto) {
        if (!isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new IllegalArgumentException("Password confirmation does not match.");
        }
        if (userDto.getPassword().length() < 8 || !userDto.getPassword().matches(".*[a-zA-Z].*")
                || !userDto.getPassword().matches(".*\\d.*")) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long and contain letters and numbers.");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        String encodedPasswordConfirmation = passwordEncoder.encode(userDto.getPasswordConfirmation());

        UserModel userModel = new UserModel (userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
                                            userDto.getUserRole(), encodedPassword, encodedPasswordConfirmation);
        userRepository.save(userModel);
        return userModel;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
