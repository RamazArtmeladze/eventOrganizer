package com.app.eventOrganizer.service;

import com.app.eventOrganizer.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.app.eventOrganizer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserRepository userRepository;

    public UserModel getAuthenticatedUsersID() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<UserModel> userModelOptional = userRepository.findByEmail(userEmail);
        UserModel userModel = userModelOptional.get();

        return userModel;
    }
}
