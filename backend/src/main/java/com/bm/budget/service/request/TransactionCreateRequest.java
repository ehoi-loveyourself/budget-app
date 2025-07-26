package com.bm.budget.service.request;

import com.bm.budget.domain.Division;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class TransactionCreateRequest {
    private Long categoryId;
    private Division division;
    private Long amount;
    private String memo;
    private LocalDate occurredAt;
}
