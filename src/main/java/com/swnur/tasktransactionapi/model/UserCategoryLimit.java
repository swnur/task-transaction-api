package com.swnur.tasktransactionapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "user_category_limit")
public class UserCategoryLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "limit_amount")
    private BigDecimal limitAmount;

    @Column(name = "remaining_limit")
    private BigDecimal remainingLimitAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private OffsetDateTime dateTime;
}
