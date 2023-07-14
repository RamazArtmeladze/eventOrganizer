package com.app.eventOrganizer.controller;

import com.app.eventOrganizer.Dto.*;
import com.app.eventOrganizer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<EventModelDto> createEventModel(@Valid  @RequestBody EventRegistrationDto eventRegistrationDto) {
        EventModelDto eventModelDto = eventService.registerEvent(eventRegistrationDto);

        return new ResponseEntity<>(eventModelDto, HttpStatus.CREATED);
    }

    @PostMapping("events/{eventId}/expenses")
    public ResponseEntity<Set<ExpenseModelDto>> addExpense(@PathVariable Long eventId, @RequestBody ExpenseRegistrationDto expenseRegistrationDto) {
        Set<ExpenseModelDto> expenseModelDto = eventService.addExpense(eventId, expenseRegistrationDto);

        return new ResponseEntity<>(expenseModelDto, HttpStatus.CREATED);
    }
}