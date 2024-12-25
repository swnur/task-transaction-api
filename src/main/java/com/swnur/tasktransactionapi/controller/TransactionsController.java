package com.swnur.tasktransactionapi.controller;

import com.swnur.tasktransactionapi.dto.DetailedTransactionsResponseDTO;
import com.swnur.tasktransactionapi.dto.TransactionsRequestDTO;
import com.swnur.tasktransactionapi.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping
    public ResponseEntity<List<DetailedTransactionsResponseDTO>> getTransactions(
            @RequestParam(required = false) Boolean limitExceeded) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionsService.getAllTransactions(limitExceeded));
    }

    @PostMapping
    public ResponseEntity<DetailedTransactionsResponseDTO> saveNewTransaction(
            @Valid @RequestBody TransactionsRequestDTO transactionsRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionsService.saveNewTransaction(transactionsRequestDTO));
    }
}
