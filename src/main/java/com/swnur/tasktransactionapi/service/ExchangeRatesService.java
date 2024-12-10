package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;
import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.model.ExchangeCurrency;
import com.swnur.tasktransactionapi.model.ExchangeRateProxyResponse;
import com.swnur.tasktransactionapi.proxy.ExchangeRatesProxy;
import com.swnur.tasktransactionapi.repository.CurrencyRepository;
import com.swnur.tasktransactionapi.repository.ExchangeCurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExchangeRatesService {

    private final ExchangeRatesProxy exchangeRatesProxy;
    private final ExchangeCurrencyRepository exchangeCurrencyRepository;
    private final CurrencyRepository currencyRepository;

    public void fetchDailyExchangeRateFromUSD() {
        Currency currencyUSD = currencyRepository.findByCode("USD")
                .orElseThrow(() -> new InvalidCurrencyCodeException("Invalid currency code was given"));
        ExchangeRateProxyResponse response = exchangeRatesProxy.getLatestExchangeRate(currencyUSD.getCode());

        List<ExchangeCurrency> targetCurrencies = exchangeCurrencyRepository.findTargetCurrenciesForBaseCurrency(currencyUSD);

        exchangeCurrencyRepository.saveAll(updateCurrencyConversionRates(targetCurrencies, response.getConversionRates()));
    }

    private List<ExchangeCurrency> updateCurrencyConversionRates(
            List<ExchangeCurrency> exchangeCurrencyList, Map<String, BigDecimal> conversionRates) {
        exchangeCurrencyList.forEach(exchangeCurrency ->
                exchangeCurrency.setRate(
                        conversionRates.get(exchangeCurrency.getTargetCurrency().getCode())
                )
        );
        return exchangeCurrencyList;
    }
}
