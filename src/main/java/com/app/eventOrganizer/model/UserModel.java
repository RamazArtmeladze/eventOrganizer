package com.app.eventOrganizer.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String email;
    String firstName;
    String lastName;
    String userRole;
    String password;
    String passwordConfirmation;

    public UserModel(String email, String firstName, String lastName, String userRole, String password, String passwordConfirmation) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }
}