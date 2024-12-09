package com.swnur.tasktransactionapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_currency")
@Data
@NoArgsConstructor
public class ExchangeCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "base_currency_id")
    private Currency baseCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private Currency targetCurrency;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "date")
    private LocalDateTime dateTime;

    public ExchangeCurrency(Currency baseCurrency, Currency targetCurrency, BigDecimal rate, LocalDateTime dateTime) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
        this.dateTime = dateTime;
    }
}
