package com.app.eventOrganizer.Dto;

import com.app.eventOrganizer.validation.PasswordConfirmationMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@PasswordConfirmationMatch
public class UserRegistrationDto {
        @NotBlank(message = "Please enter email")
        @Email(message = "Invalid email address")
        private String email;
        @NotBlank(message = "Please enter name")
        private String firstName;
        @NotBlank(message = "Please enter last name")
        private String lastName;
        private String userRole;
        @NotBlank(message = "Please enter password")
        @Size(min=8, message = "Password must be at least 8 symbols")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",
                message = "Password must contain both letters and numbers"
        )
        private String password;
        @NotBlank(message = "Password confirmation is required")
        private String passwordConfirmation;

}

