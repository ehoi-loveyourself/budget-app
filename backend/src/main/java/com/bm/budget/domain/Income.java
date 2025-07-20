package com.bm.budget.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Income extends BaseEntity {

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
    private LocalDate receivedAt;

    @Builder
    public Income(User user, Category category, Long amount, String memo, LocalDate receivedAt) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.memo = memo;
        this.receivedAt = receivedAt;
    }
}
