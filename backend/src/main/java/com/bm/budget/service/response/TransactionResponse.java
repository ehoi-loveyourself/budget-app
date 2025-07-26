package com.bm.budget.service.response;

import com.bm.budget.domain.Ledger;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@Getter
public class TransactionResponse {
    private Long userId;
    private Long categoryId;
    private String division;
    private Long amount;
    private String memo;
    private LocalDate occurredAt;

    public static TransactionResponse from(Ledger ledger) {
        return TransactionResponse.builder()
                .userId(ledger.getUser().getId())
                .categoryId(ledger.getCategory().getId())
                .amount(ledger.getAmount())
                .memo(ledger.getMemo())
                .occurredAt(ledger.getOccurredAt())
                .build();
    }
}
