package com.app.eventOrganizer.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExpensesModelDto {
    private Long id;
    private String description;
    private Double amount;
}
