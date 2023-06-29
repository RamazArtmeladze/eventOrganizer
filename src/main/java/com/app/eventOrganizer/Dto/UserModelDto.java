package com.app.eventOrganizer.Dto;

import com.app.eventOrganizer.validation.PasswordConfirmationMatch;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@PasswordConfirmationMatch
@Builder
public class UserModelDto {
        private Long id;

        @NotBlank(message = "Please enter email")
        @Email(message = "Invalid email address")
        private String email;

        @NotBlank(message = "Please enter name")
        private String firstName;

        @NotBlank(message = "Please enter last name")
        private String lastName;
}

