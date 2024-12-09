package com.swnur.tasktransactionapi.dto;

import com.swnur.tasktransactionapi.model.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;


@Data
public class DetailedTransactionsResponseDTO {

    private BigInteger accountFrom;
    private BigInteger accountTo;
    private String currencyShortname;
    private BigDecimal amount;
    private OffsetDateTime dateTime;
    private BigDecimal limitAmount;
    private BigDecimal remainingLimitAmount;
    private Boolean limitExceeded;
    private OffsetDateTime limitDateTime;
    private String limitCurrencyShortname;

    public DetailedTransactionsResponseDTO(Transaction transaction) {
        this.accountFrom = transaction.getSendingUser().getAccount();
        this.accountTo = transaction.getReceivingUser().getAccount();
        this.currencyShortname = transaction.getCurrency().getCode();
        this.amount = transaction.getAmount();
        this.dateTime = transaction.getCreatedAt();
        this.limitAmount = transaction.getCategory().getLimitAmount();
        this.remainingLimitAmount = transaction.getCategory().getRemainingLimitAmount();
        this.limitExceeded = transaction.getLimitExceeded();
        this.limitDateTime = transaction.getCategory().getDateTime();
        this.limitCurrencyShortname = "USD";
    }
}
