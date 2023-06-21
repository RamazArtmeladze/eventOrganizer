package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.mapper.UserModelMapper;
import com.app.eventOrganizer.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        UserModelMapper mapper = new UserModelMapper(passwordEncoder);
        userService = new UserService(userRepository, mapper);
    }
    @Test
    void registerUser() {
        UserModelDto expectedUser = UserModelDto.builder()
                .email("userd@das.com")
                .firstName("user")
                .lastName("name")
                .userRole("user")
                .password("paroli123")
                .passwordConfirmation("paroli123")
                .build();

        Mockito.when(userRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        UserModelDto actualUser = userService.registerUser(expectedUser);

        Assertions.assertEquals(actualUser.getFirstName(), expectedUser.getFirstName());
        Assertions.assertEquals(actualUser.getLastName(), expectedUser.getLastName());
        Assertions.assertEquals(actualUser.getUserRole(), expectedUser.getUserRole());
        Assertions.assertEquals(actualUser.getEmail(), expectedUser.getEmail());

    }
}