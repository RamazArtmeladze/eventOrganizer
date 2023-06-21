package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;

    public UserModelDto registerUser(UserModelDto userDto) {

        UserModel userModel = mapper.toEntity(userDto);

        return mapper.toDto(userRepository.save(userModel));
    }
}
