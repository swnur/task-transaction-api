package com.swnur.tasktransactionapi.repository;

import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.model.ExchangeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeCurrencyRepository extends JpaRepository<ExchangeCurrency, Long> {

    @Query("SELECT e.rate FROM ExchangeCurrency e WHERE e.baseCurrency = :baseCurrency AND e.targetCurrency =:targetCurrency")
    BigDecimal findRateByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);

    @Query("SELECT e FROM ExchangeCurrency e WHERE e.baseCurrency = :baseCurrency")
    List<ExchangeCurrency> findTargetCurrenciesForBaseCurrency(Currency baseCurrency);
}
