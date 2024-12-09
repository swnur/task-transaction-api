package com.swnur.tasktransactionapi.exception;

public class InvalidUserAccountException extends RuntimeException {
    public InvalidUserAccountException(String message) {
        super(message);
    }
}
