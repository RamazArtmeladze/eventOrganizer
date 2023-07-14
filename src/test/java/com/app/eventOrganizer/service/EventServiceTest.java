package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.EventModelDto;
import com.app.eventOrganizer.Dto.EventRegistrationDto;
import com.app.eventOrganizer.Dto.ExpenseModelDto;
import com.app.eventOrganizer.Dto.ExpenseRegistrationDto;
import com.app.eventOrganizer.mapper.EventModelMapper;
import com.app.eventOrganizer.mapper.ExpenseModelMapper;
import com.app.eventOrganizer.model.EventModel;
import com.app.eventOrganizer.model.ExpenseModel;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ExpenseModelMapper expenseModelMapper;
    @Mock
    private EventModelMapper mapper;
    @Mock
    private UserDataService userDataService;

    @BeforeEach
    void setUp() {
        eventService = new EventService(eventRepository, expenseModelMapper, mapper, userDataService);
    }

    @Test
    void registerEvent() {
        EventRegistrationDto eventRegistrationDto = EventRegistrationDto.builder()
                .name("user")
                .startDate(LocalDate.parse("2023-08-09"))
                .endDate(LocalDate.parse("2023-08-09"))
                .location("tbilisi")
                .description("description")
                .estimateBudget(BigDecimal.valueOf(1000.00))
                .build();

        EventModel eventModel = EventModel.builder()
                .name(eventRegistrationDto.getName())
                .startDate(eventRegistrationDto.getStartDate())
                .endDate(eventRegistrationDto.getEndDate())
                .location(eventRegistrationDto.getLocation())
                .description(eventRegistrationDto.getDescription())
                .estimateBudget(eventRegistrationDto.getEstimateBudget())
                .build();

        EventModelDto expectedEventDto = EventModelDto.builder()
                .name(eventModel.getName())
                .startDate(eventModel.getStartDate())
                .endDate(eventModel.getEndDate())
                .location(eventModel.getLocation())
                .description(eventModel.getDescription())
                .estimateBudget(eventModel.getEstimateBudget())
                .build();

        when(mapper.toEntity(eventRegistrationDto)).thenReturn(eventModel);
        when(userDataService.getAuthenticatedUserID()).thenReturn(UserModel.builder().userId(1L).build());
        when(eventRepository.save(eventModel)).thenReturn(eventModel);
        when(mapper.toDto(eventModel)).thenReturn(expectedEventDto);

        EventModelDto actualEventDto = eventService.registerEvent(eventRegistrationDto);

        Assertions.assertEquals(expectedEventDto.getName(), actualEventDto.getName());
        Assertions.assertEquals(expectedEventDto.getStartDate(), actualEventDto.getStartDate());
        Assertions.assertEquals(expectedEventDto.getEndDate(), actualEventDto.getEndDate());
        Assertions.assertEquals(expectedEventDto.getLocation(), actualEventDto.getLocation());
        Assertions.assertEquals(expectedEventDto.getDescription(), actualEventDto.getDescription());
        Assertions.assertEquals(expectedEventDto.getEstimateBudget(), actualEventDto.getEstimateBudget());
    }

    @Test
    void addExpense() {
        Long eventId = 1L;

        ExpenseRegistrationDto expenseRegistrationDto = ExpenseRegistrationDto.builder()
                .amount(100.00)
                .description("Expense 1")
                .build();

        ExpenseModel expenseModel = ExpenseModel.builder()
                .amount(expenseRegistrationDto.getAmount())
                .description(expenseRegistrationDto.getDescription())
                .build();

        ExpenseModelDto expectedExpenseDto = ExpenseModelDto.builder()
                .id(expenseModel.getExpensesId())
                .amount(expenseModel.getAmount())
                .description(expenseModel.getDescription())
                .build();

        Set<ExpenseModelDto> expectedExpenseDtoSet = new HashSet<>();
        expectedExpenseDtoSet.add(expectedExpenseDto);

        when(eventRepository.getReferenceById(eventId)).thenReturn(Mockito.mock(EventModel.class));
        when(expenseModelMapper.toEntity(expenseRegistrationDto)).thenReturn(expenseModel);
        when(userDataService.getAuthenticatedUserID()).thenReturn(UserModel.builder().userId(1L).build());
        when(eventRepository.save(any())).thenReturn(Mockito.mock(EventModel.class));
        when(expenseModelMapper.toDtoSet(any(Set.class))).thenReturn(expectedExpenseDtoSet);

        Set<ExpenseModelDto> actualExpenseDtoSet = eventService.addExpense(eventId, expenseRegistrationDto);

        Assertions.assertNotNull(actualExpenseDtoSet);
        Assertions.assertEquals(1, actualExpenseDtoSet.size());
        Assertions.assertTrue(actualExpenseDtoSet.contains(expectedExpenseDto));
    }
}