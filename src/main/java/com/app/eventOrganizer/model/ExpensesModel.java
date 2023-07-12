package com.app.eventOrganizer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "expenses_model")
public class ExpensesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expensesId;
    private Double amount;
    private String description;
    private Long createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventModel event;
}
