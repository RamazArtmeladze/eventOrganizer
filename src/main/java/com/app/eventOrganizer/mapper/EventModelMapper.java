package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.EventModelDto;
import com.app.eventOrganizer.Dto.EventRegistrationDto;
import com.app.eventOrganizer.Dto.ExpensesModelDto;
import com.app.eventOrganizer.model.EventModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@NoArgsConstructor
public class EventModelMapper {

    @Autowired
    private ExpensesModelMapper expensesModelMapper;
    public EventModelDto toDto (EventModel eventModel) {

        return EventModelDto.builder()
                .id(eventModel.getEventId())
                .name(eventModel.getName())
                .startDate(eventModel.getStartDate())
                .endDate(eventModel.getEndDate())
                .location(eventModel.getLocation())
                .description(eventModel.getDescription())
                .estimateBudget(eventModel.getEstimateBudget())
                .expenses(eventModel.getExpenses() == null? Set.of(new ExpensesModelDto(0L, "No Expenses", 0D)):expensesModelMapper.toDtos(eventModel.getExpenses()))
                .build();
    }

    public EventModel toEntity (EventRegistrationDto eventRegistrationDto) {
        return EventModel.builder()
                .name(eventRegistrationDto.getName())
                .startDate(eventRegistrationDto.getStartDate())
                .endDate(eventRegistrationDto.getEndDate())
                .location(eventRegistrationDto.getLocation())
                .description(eventRegistrationDto.getDescription())
                .estimateBudget((eventRegistrationDto.getEstimateBudget()))
                .build();
    }
}
