package com.app.eventOrganizer.mapper;


import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserModelMapper {
    @Autowired
    private PasswordEncoder passwordEncoder ;

    public UserModelDto toDto (UserModel userModel) {
        return UserModelDto.builder()
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .userRole(userModel.getUserRole())
                .password(userModel.getPassword())
                .passwordConfirmation(userModel.getPasswordConfirmation())
                .build();
    }
    public  UserModel toEntity (UserModelDto userModelDto) {
        String encodedPassword = passwordEncoder.encode(userModelDto.getPassword());
        String encodedPasswordConfirmation = passwordEncoder.encode(userModelDto.getPasswordConfirmation());

        return UserModel.builder()
                .email(userModelDto.getEmail())
                .firstName(userModelDto.getFirstName())
                .lastName(userModelDto.getLastName())
                .userRole(userModelDto.getUserRole())
                .password(encodedPassword)
                .passwordConfirmation(encodedPasswordConfirmation)
                .build();
    }
}
