package com.app.eventOrganizer.validation;

import com.app.eventOrganizer.Dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationMatchValidator implements ConstraintValidator<PasswordConfirmationMatch, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userDto, ConstraintValidatorContext context) {
        if (userDto == null) {
            return true;
        }
        return userDto.getPassword().equals(userDto.getPasswordConfirmation());
    }
}
