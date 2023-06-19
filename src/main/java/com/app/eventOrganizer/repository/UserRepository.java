package com.app.eventOrganizer.repository;

import com.app.eventOrganizer.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, Long> {
}
