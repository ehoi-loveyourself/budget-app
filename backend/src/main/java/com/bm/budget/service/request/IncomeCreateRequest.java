package com.bm.budget.service.request;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class IncomeCreateRequest {
    private Long categoryId;
    private Long amount;
    private String memo;
    private LocalDate transactedAt;
}
