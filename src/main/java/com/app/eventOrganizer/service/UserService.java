package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;

    public UserModelDto registerUser(UserRegistrationDto userRegistrationDto) {

        UserModel userModel = mapper.toEntity(userRegistrationDto);
        UserModel savedUserModel = userRepository.save(userModel);

        return mapper.toDto(userRepository.save(userModel));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isValidUser(String email, String password) {
        UserModel user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            return true;
        }

        return false;
    }
}
