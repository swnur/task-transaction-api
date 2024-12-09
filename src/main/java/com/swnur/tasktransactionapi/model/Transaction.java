package com.swnur.tasktransactionapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sending_user_id")
    private User sendingUser;

    @ManyToOne
    @JoinColumn(name = "receiving_user_id")
    private User receivingUser;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_category_limit_id")
    private UserCategoryLimit category;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    public Transaction(User sendingUser, User receivingUser, Currency currency, UserCategoryLimit category, BigDecimal amount, OffsetDateTime createdAt, Boolean limitExceeded) {
        this.sendingUser = sendingUser;
        this.receivingUser = receivingUser;
        this.currency = currency;
        this.category = category;
        this.amount = amount;
        this.createdAt = createdAt;
        this.limitExceeded = limitExceeded;
    }
}
