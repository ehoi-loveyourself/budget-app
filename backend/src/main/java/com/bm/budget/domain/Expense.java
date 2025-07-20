package com.bm.budget.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense")
@Entity
public class Expense extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long amount;

    private String memo;

    @Column(nullable = false)
    private LocalDate spentAt;

    @Builder
    public Expense(User user, Category category, Long amount, String memo, LocalDate spentAt) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.memo = memo;
        this.spentAt = spentAt;
    }
}
