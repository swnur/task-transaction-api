package com.swnur.tasktransactionapi.utils;

import com.swnur.tasktransactionapi.exception.InvalidBigDecimalAmount;
import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @ParameterizedTest
    @CsvSource({ "null", "-10" })
    @DisplayName("Should throw an InvalidBigDecimalAmount exception if the value is null or negative value")
    void testIsValidBigDecimalAmountForInvalidValues(String amountStr) {
        BigDecimal amount = "null".equals(amountStr) ? null : new BigDecimal(amountStr);

        assertThrows(InvalidBigDecimalAmount.class, () -> ValidationUtils.isValidBigDecimalAmount(amount));
    }

    @Test
    @DisplayName("Should not throw an InvalidBigDecimalAmount exception if the value is not null and positive value")
    void testIsValidBigDecimalAmountForPositiveNotNullValue() {
        assertDoesNotThrow(() -> ValidationUtils.isValidBigDecimalAmount(BigDecimal.ZERO));
        assertDoesNotThrow(() -> ValidationUtils.isValidBigDecimalAmount(BigDecimal.valueOf(100L)));
    }

    @ParameterizedTest
    @CsvSource({"usd", "usa"})
    @DisplayName("Should throw an InvalidCurrencyCodeException if currency code length not equal to 3")
    void testIsValidCurrencyCodeForInvalidValues(String testCurrencyCode) {
        assertThrows(InvalidCurrencyCodeException.class,
                () -> ValidationUtils.isValidCurrencyCodeName(testCurrencyCode));
    }

    @ParameterizedTest
    @CsvSource({"USD", "KZT", "RUB"})
    @DisplayName("Should not throw an InvalidCurrencyCodeException for the valid currency code in ISO 4217 format.")
    void testIsValidCurrencyCodeForValidValues(String testCurrencyCode) {
        assertDoesNotThrow(() -> ValidationUtils.isValidCurrencyCodeName(testCurrencyCode));
    }
}