package com.app.eventOrganizer.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {
    public UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel saveToDB(Long ID, String email, String firstName, String lastName, String userRole,
                              String password, String passwordConfirmation) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Password confirmation does not match.");
        }
        if (password.length() < 8 || !password.matches(".*[a-zA-Z].*")
                || !password.matches(".*\\d.*")) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long and contain letters and numbers.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        String encodedPasswordConfirmation = passwordEncoder.encode(passwordConfirmation);

        UserModel userModel = new UserModel(ID, email, firstName, lastName, userRole, encodedPassword, encodedPasswordConfirmation);
        userRepository.save(userModel);
        return userModel;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserByEmail(String email) {
        UserModel user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return user;
    }

    public void deleteUserByEmail(String email) {
        UserModel user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        userRepository.delete(user);
    }

    public UserModel updateUserByEmail(String email, UserModel updatedUser) {
        UserModel existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setUserRole(updatedUser.getUserRole());

        return userRepository.save(existingUser);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
