package com.app.eventOrganizer.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExpenseModelDto {
    private Long id;
    private String description;
    private Double amount;
}
