package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.dto.DetailedTransactionsResponseDTO;
import com.swnur.tasktransactionapi.dto.TransactionsRequestDTO;
import com.swnur.tasktransactionapi.dto.TransactionsResponseDTO;
import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.model.Transaction;
import com.swnur.tasktransactionapi.model.User;
import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import com.swnur.tasktransactionapi.repository.*;
import com.swnur.tasktransactionapi.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionsService {

    private final ExchangeCurrencyService exchangeCurrencyService;
    private final TransactionsRepository transactionsRepository;
    private final UserService userService;
    private final CurrencyService currencyService;
    private final UserCategoryLimitService userCategoryLimitService;

    public List<DetailedTransactionsResponseDTO> getAllTransactions() {
        List<Transaction> list = transactionsRepository.findAll();
        return list
                .stream()
                .map(DetailedTransactionsResponseDTO::new)
                .toList();
    }

    public List<TransactionsResponseDTO> getAllTransactionsWith(Boolean limitExceeded) {
        List<Transaction> list = transactionsRepository.findAllByLimitExceeded(limitExceeded);
        return list
                .stream()
                .map(TransactionsResponseDTO::new)
                .toList();
    }

    public DetailedTransactionsResponseDTO saveNewTransaction(TransactionsRequestDTO transactionsRequestDTO) {
        ValidationUtils.validateBigDecimalAmount(transactionsRequestDTO.getAmount());

        Transaction transaction = new Transaction();

        User userAccountFrom = userService.findByAccount(transactionsRequestDTO.getAccountFrom());
        transaction.setSendingUser(userAccountFrom);

        User userAccountTo = userService.findByAccount(transactionsRequestDTO.getAccountTo());
        transaction.setReceivingUser(userAccountTo);

        Currency currency = currencyService.findCurrencyByCurrencyCode(transactionsRequestDTO.getCurrencyShortName());
        transaction.setCurrency(currency);

        BigDecimal convertedAmount = exchangeCurrencyService.convertToUSD(
                transactionsRequestDTO.getAmount(), transactionsRequestDTO.getCurrencyShortName());
        UserCategoryLimit categoryLimit = userCategoryLimitService.updateRemainingLimit(
                userAccountFrom.getId(), transactionsRequestDTO.getCategoryType(),convertedAmount);
        transaction.setCategory(categoryLimit);

        transaction.setAmount(transactionsRequestDTO.getAmount());
        transaction.setCreatedAt(OffsetDateTime.now());
        transaction.setLimitExceeded(categoryLimit.getRemainingLimitAmount().compareTo(BigDecimal.ZERO) < 0);

        return new DetailedTransactionsResponseDTO(transactionsRepository.save(transaction));
    }

}
