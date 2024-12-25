package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.dto.DetailedTransactionsResponseDTO;
import com.swnur.tasktransactionapi.dto.TransactionsRequestDTO;
import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.model.Transaction;
import com.swnur.tasktransactionapi.model.User;
import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import com.swnur.tasktransactionapi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    public List<DetailedTransactionsResponseDTO> getAllTransactions(Boolean limitExceeded) {
        List<Transaction> list = limitExceeded == null ?
                transactionsRepository.findAll() : transactionsRepository.findAllByLimitExceeded(limitExceeded);
        return list
                .stream()
                .map(DetailedTransactionsResponseDTO::new)
                .toList();
    }

    @Transactional
    public DetailedTransactionsResponseDTO saveNewTransaction(TransactionsRequestDTO transactionsRequestDTO) {
        Transaction transaction = new Transaction();

        User userAccountFrom = findUser(transactionsRequestDTO.getAccountFrom());
        transaction.setSendingUser(userAccountFrom);

        User userAccountTo = findUser(transactionsRequestDTO.getAccountTo());
        transaction.setReceivingUser(userAccountTo);

        Currency currency = currencyService.findCurrencyByCurrencyCode(transactionsRequestDTO.getCurrencyCode());
        transaction.setCurrency(currency);

        BigDecimal convertedAmount = exchangeCurrencyService.convertToUSD(
                transactionsRequestDTO.getAmount(), transactionsRequestDTO.getCurrencyCode());
        UserCategoryLimit categoryLimit = userCategoryLimitService.updateRemainingLimit(
                userAccountFrom.getId(), transactionsRequestDTO.getCategoryType(),convertedAmount);
        transaction.setCategory(categoryLimit);

        transaction.setAmount(transactionsRequestDTO.getAmount());
        transaction.setCreatedAt(OffsetDateTime.now());
        transaction.setLimitExceeded(categoryLimit.getRemainingLimitAmount().compareTo(BigDecimal.ZERO) < 0);

        return new DetailedTransactionsResponseDTO(transactionsRepository.save(transaction));
    }

    private User findUser(BigInteger account) {
        return userService.findByAccount(account);
    }
}
