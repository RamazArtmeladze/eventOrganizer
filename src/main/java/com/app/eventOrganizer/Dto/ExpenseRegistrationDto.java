package com.app.eventOrganizer.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExpenseRegistrationDto {
    private Double amount;
    private String description;
}
