package com.bm.budget.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ledgers")
@Entity
public class Ledger extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Division division; // EXPENSE or INCOME

    @Column(nullable = false)
    private Long amount;

    private String memo;

    @Column(nullable = false)
    private LocalDate occurredAt;

    @Builder
    public Ledger(User user, Category category, Division division, Long amount, String memo, LocalDate occurredAt) {
        this.user = user;
        this.category = category;
        this.division = division;
        this.amount = amount;
        this.memo = memo;
        this.occurredAt = occurredAt;
    }
}
