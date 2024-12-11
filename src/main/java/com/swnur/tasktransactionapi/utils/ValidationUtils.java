package com.swnur.tasktransactionapi.utils;

import com.swnur.tasktransactionapi.exception.InvalidBigDecimalAmount;
import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;

import java.math.BigDecimal;
import java.util.Currency;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void isValidBigDecimalAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBigDecimalAmount("BigDecimal amount can not be null or less than zero.");
        }
    }

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
