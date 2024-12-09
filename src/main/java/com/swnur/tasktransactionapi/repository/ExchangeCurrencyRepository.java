package com.swnur.tasktransactionapi.repository;

import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.model.ExchangeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ExchangeCurrencyRepository extends JpaRepository<ExchangeCurrency, Long> {

    @Query("SELECT e.rate FROM ExchangeCurrency e WHERE e.baseCurrency = :baseCurrency AND e.targetCurrency =:targetCurrency")
    BigDecimal findByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);
}
