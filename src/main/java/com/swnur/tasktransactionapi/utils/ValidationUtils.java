package com.swnur.tasktransactionapi.utils;

import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;

import java.util.Currency;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void isValidCurrencyCodeName(String currencyCode) {
        if (!isCurrencyCodeNameInCorrectFormat(currencyCode)) {
            throw new InvalidCurrencyCodeException("Invalid currency code. Currency code has to be in ISO 4217 format.");
        }
    }

    private static boolean isCurrencyCodeNameInCorrectFormat(String currencyCode) {
        return Currency.getAvailableCurrencies()
                .stream()
                .anyMatch(currency -> currency.getCurrencyCode().equals(currencyCode));
    }
}
