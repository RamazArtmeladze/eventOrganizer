package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.ExpensesModelDto;
import com.app.eventOrganizer.Dto.ExpensesRegistrationDto;
import com.app.eventOrganizer.model.ExpensesModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class ExpensesModelMapper {
    public ExpensesModelDto toDto (ExpensesModel expensesModel) {

        return ExpensesModelDto.builder()
                .id(expensesModel.getExpensesId())
                .amount(expensesModel.getAmount())
                .description(expensesModel.getDescription())
                .build();
    }

    public Set<ExpensesModelDto> toDtos(Set<ExpensesModel> expensesModels){
        return expensesModels.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public ExpensesModel toEntity (ExpensesRegistrationDto expensesRegistrationDto) {
        return ExpensesModel.builder()
                .description(expensesRegistrationDto.getDescription())
                .amount(expensesRegistrationDto.getAmount())
                .build();
    }
}
