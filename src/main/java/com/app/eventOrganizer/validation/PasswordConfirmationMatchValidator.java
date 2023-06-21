package com.app.eventOrganizer.validation;

import com.app.eventOrganizer.Dto.UserModelDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationMatchValidator implements ConstraintValidator<PasswordConfirmationMatch, UserModelDto> {

    @Override
    public boolean isValid(UserModelDto userDto, ConstraintValidatorContext context) {
        if (userDto == null) {
            return true;
        }
        return userDto.getPassword().equals(userDto.getPasswordConfirmation());
    }
}
