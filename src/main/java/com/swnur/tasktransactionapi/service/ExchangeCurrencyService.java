package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;
import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.repository.CurrencyRepository;
import com.swnur.tasktransactionapi.repository.ExchangeCurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ExchangeCurrencyService {

    private final ExchangeCurrencyRepository exchangeCurrencyRepository;
    private final CurrencyRepository currencyRepository;

    public BigDecimal convertToUSD(BigDecimal amount, String currencyCode) {
        Currency currencyFrom = currencyRepository.findByCode(currencyCode)
                .orElseThrow(() -> new InvalidCurrencyCodeException(currencyCode + " does not exist in the currency table"));
        Currency currencyUSD = currencyRepository.findByCode("USD")
                .orElseThrow(() -> new InvalidCurrencyCodeException("USD does not exist in the currency table"));
        BigDecimal rateFromCurrencyCodeToUSD = exchangeCurrencyRepository.findRateByBaseCurrencyAndTargetCurrency(currencyFrom, currencyUSD);
        return amount.multiply(rateFromCurrencyCodeToUSD);
    }
}
