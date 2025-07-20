package com.bm.budget.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type; // EXPENSE or INCOME

    private String color;

    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // null이면 공용 카테고리

    @Builder
    public Category(String name, CategoryType type, String color, String icon, User user) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.icon = icon;
        this.user = user;
    }
}
