package com.swnur.tasktransactionapi.advice;

import com.swnur.tasktransactionapi.exception.InvalidBigDecimalAmount;
import com.swnur.tasktransactionapi.exception.InvalidCurrencyCodeException;
import com.swnur.tasktransactionapi.exception.InvalidUserAccountException;
import com.swnur.tasktransactionapi.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, InvalidBigDecimalAmount.class,
            InvalidCurrencyCodeException.class, InvalidUserAccountException.class})
    public ResponseEntity<ErrorDetails> exceptionMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
