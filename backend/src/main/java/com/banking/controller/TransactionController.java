package com.banking.controller;

import com.banking.dto.ApiResponse;
import com.banking.dto.TransactionRequest;
import com.banking.model.Transaction;
import com.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST Controller for transaction endpoints
 */
@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Deposit money
     * POST /api/transactions/deposit
     */
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.deposit(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Deposit successful", transaction));
    }

    /**
     * Withdraw money
     * POST /api/transactions/withdraw
     */
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.withdraw(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Withdrawal successful", transaction));
    }

    /**
     * Transfer money
     * POST /api/transactions/transfer
     */
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.transfer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Transfer successful", transaction));
    }

    /**
     * Get transaction history for an account
     * GET /api/transactions/account/{accountNumber}
     */
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<ApiResponse> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);

        return ResponseEntity
                .ok(ApiResponse.success("Transaction history retrieved successfully", transactions));
    }
}
