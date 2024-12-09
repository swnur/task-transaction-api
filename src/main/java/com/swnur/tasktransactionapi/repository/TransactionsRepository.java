package com.swnur.tasktransactionapi.repository;

import com.swnur.tasktransactionapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByLimitExceeded(Boolean limitExceeded);
}

