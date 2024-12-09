package com.swnur.tasktransactionapi.utils;

import com.swnur.tasktransactionapi.exception.InvalidBigDecimalAmount;

import java.math.BigDecimal;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void validateBigDecimalAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBigDecimalAmount("BigDecimal amount can not be null or less than zero");
        }
    }
}
