package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.EventModelDto;
import com.app.eventOrganizer.Dto.EventRegistrationDto;
import com.app.eventOrganizer.Dto.ExpensesRegistrationDto;
import com.app.eventOrganizer.mapper.EventModelMapper;
import com.app.eventOrganizer.mapper.ExpensesModelMapper;
import com.app.eventOrganizer.model.EventModel;
import com.app.eventOrganizer.model.ExpensesModel;
import com.app.eventOrganizer.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ExpensesModelMapper expensesModelMapper;
    private final EventModelMapper mapper;
    private final UserDataService userDataService;

    public EventModelDto registerEvent(EventRegistrationDto eventRegistrationDto) {

        EventModel eventModel = mapper.toEntity(eventRegistrationDto);
        Long createdBy = userDataService.getAuthenticatedUsersID().getUserId();
        eventModel.setCreatedBy(createdBy);

        if (eventRegistrationDto.getExpenses() != null) {
            ExpensesModel expensesModel = expensesModelMapper.toEntity(eventRegistrationDto.getExpenses());
            if (eventModel.getExpenses() == null || eventModel.getExpenses().isEmpty()) {
                eventModel.setExpenses(Set.of(expensesModel));
            } else {
                eventModel.getExpenses().add(expensesModel);
            }
            expensesModel.setCreatedBy(createdBy);
        }

        return mapper.toDto(eventRepository.save(eventModel));
    }

    public EventModelDto addExpense(Long eventId, ExpensesRegistrationDto expensesRegistrationDto) {
        EventModel eventModel = eventRepository.getReferenceById(eventId);
        Long createdBy = userDataService.getAuthenticatedUsersID().getUserId();
        ExpensesModel expensesModel = expensesModelMapper.toEntity(expensesRegistrationDto);

        if (eventModel.getExpenses() == null || eventModel.getExpenses().isEmpty()) {
            eventModel.setExpenses(new HashSet<ExpensesModel>());
            eventModel.getExpenses().add(expensesModel);
        } else {
            eventModel.getExpenses().add(expensesModel);
        }
        expensesModel.setCreatedBy(createdBy);
        eventRepository.save(eventModel);

        return mapper.toDto(eventModel);
    }
}
