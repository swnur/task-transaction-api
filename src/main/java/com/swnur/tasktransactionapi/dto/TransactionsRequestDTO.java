package com.swnur.tasktransactionapi.dto;

import com.swnur.tasktransactionapi.model.CategoryType;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Data
public class TransactionsRequestDTO {

    private BigInteger accountFrom;
    private BigInteger accountTo;
    private String currencyShortName;
    private BigDecimal amount;
    private CategoryType categoryType;
    private OffsetDateTime dateTime;
}
