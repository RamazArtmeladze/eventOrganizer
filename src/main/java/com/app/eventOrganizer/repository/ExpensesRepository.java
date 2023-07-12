package com.app.eventOrganizer.repository;

import com.app.eventOrganizer.model.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {

}
