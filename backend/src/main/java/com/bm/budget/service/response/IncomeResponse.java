package com.bm.budget.service.response;

import com.bm.budget.domain.Income;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@Getter
public class IncomeResponse {
    private Long userId;
    private Long categoryId;
    private Long amount;
    private String memo;
    private LocalDate transactedAt;

    public static IncomeResponse from(Income income) {
        return IncomeResponse.builder()
                .userId(income.getUser().getId())
                .categoryId(income.getCategory().getId())
                .amount(income.getAmount())
                .memo(income.getMemo())
                .transactedAt(income.getReceivedAt())
                .build();
    }
}
