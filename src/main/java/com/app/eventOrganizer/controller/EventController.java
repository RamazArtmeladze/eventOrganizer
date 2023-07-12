package com.app.eventOrganizer.controller;

import com.app.eventOrganizer.Dto.EventModelDto;
import com.app.eventOrganizer.Dto.EventRegistrationDto;
import com.app.eventOrganizer.Dto.ExpensesRegistrationDto;
import com.app.eventOrganizer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    public ResponseEntity<EventModelDto> addExpense(@PathVariable Long eventId, @RequestBody ExpensesRegistrationDto expensesRegistrationDto) {
        EventModelDto eventModelDto = eventService.addExpense(eventId, expensesRegistrationDto);

        return new ResponseEntity<>(eventModelDto, HttpStatus.CREATED);
    }
}