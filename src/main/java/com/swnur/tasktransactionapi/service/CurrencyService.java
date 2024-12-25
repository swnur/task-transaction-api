package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;
import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency findCurrencyByCurrencyCode(String currencyCode) {
        return currencyRepository.findByCode(currencyCode)
                .orElseThrow(() -> new InvalidCurrencyCodeException("Invalid currency code was given " + currencyCode));
    }
}
