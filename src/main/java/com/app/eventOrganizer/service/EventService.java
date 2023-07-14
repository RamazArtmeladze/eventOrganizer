package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.*;
import com.app.eventOrganizer.mapper.EventModelMapper;
import com.app.eventOrganizer.mapper.ExpenseModelMapper;
import com.app.eventOrganizer.model.EventModel;
import com.app.eventOrganizer.model.ExpenseModel;
import com.app.eventOrganizer.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ExpenseModelMapper expenseModelMapper;
    private final EventModelMapper mapper;
    private final UserDataService userDataService;

    public EventModelDto registerEvent(EventRegistrationDto eventRegistrationDto) {

        EventModel eventModel = mapper.toEntity(eventRegistrationDto);
        Long createdBy = userDataService.getAuthenticatedUserID().getUserId();
        eventModel.setCreatedBy(createdBy);

        return mapper.toDto(eventRepository.save(eventModel));
    }

    @Transactional
    public Set<ExpenseModelDto> addExpense(Long eventId, ExpenseRegistrationDto expenseRegistrationDto) {
        EventModel eventModel = eventRepository.getReferenceById(eventId);
        Long createdBy = userDataService.getAuthenticatedUserID().getUserId();
        ExpenseModel expenseModel = expenseModelMapper.toEntity(expenseRegistrationDto);

        if (eventModel.getExpenses() == null || eventModel.getExpenses().isEmpty()) {
            eventModel.setExpenses(new HashSet<>());
        }
        eventModel.getExpenses().add(expenseModel);

        expenseModel.setCreatedBy(createdBy);

        return expenseModelMapper.toDtoSet(eventRepository.save(eventModel).getExpenses());
    }
}
