package com.swnur.tasktransactionapi.exception;

public class InvalidBigDecimalAmount extends RuntimeException {
    public InvalidBigDecimalAmount(String message) {
        super(message);
    }
}
