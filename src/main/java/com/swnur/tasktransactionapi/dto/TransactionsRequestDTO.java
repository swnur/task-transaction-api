package com.swnur.tasktransactionapi.dto;

import com.swnur.tasktransactionapi.model.CategoryType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Data
public class TransactionsRequestDTO {

    @NotNull(message = "'accountFrom' value can not be null")
    private BigInteger accountFrom;

    @NotNull(message = "'accountTo' value can not be null")
    private BigInteger accountTo;

    @NotNull(message = "'currencyCode' value can not be null")
    @Size(min = 3, max = 3, message = "currencyCode value's length must be 3")
    private String currencyCode;

    @NotNull(message = "'amount' value can not be null")
    @DecimalMin(value = "0.00", message = "'amount' value can be less than zero")
    private BigDecimal amount;

    @NotNull(message = "'categoryType' can not be null")
    private CategoryType categoryType;

    private OffsetDateTime dateTime;
}
