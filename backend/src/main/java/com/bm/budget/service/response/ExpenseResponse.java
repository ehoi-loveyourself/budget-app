package com.bm.budget.service.response;

import com.bm.budget.domain.Expense;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@Getter
public class ExpenseResponse {
    private Long userId;
    private Long categoryId;
    private Long amount;
    private String memo;
    private LocalDate transactedAt;

    public static ExpenseResponse from(Expense expense) {
        return ExpenseResponse.builder()
                .userId(expense.getUser().getId())
                .categoryId(expense.getCategory().getId())
                .amount(expense.getAmount())
                .memo(expense.getMemo())
                .transactedAt(expense.getSpentAt())
                .build();
    }
}
