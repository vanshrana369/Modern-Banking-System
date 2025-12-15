package com.banking.controller;

import com.banking.dto.AccountRequest;
import com.banking.dto.ApiResponse;
import com.banking.model.Account;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for account management endpoints
 */
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Create a new account
     * POST /api/accounts/create
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        Account account = accountService.createAccount(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Account created successfully", account));
    }

    /**
     * Get all accounts for a user
     * GET /api/accounts/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserAccounts(@PathVariable Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);

        return ResponseEntity
                .ok(ApiResponse.success("Accounts retrieved successfully", accounts));
    }

    /**
     * Get account by account number
     * GET /api/accounts/{accountNumber}
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);

        return ResponseEntity
                .ok(ApiResponse.success("Account retrieved successfully", account));
    }

    /**
     * Get account balance
     * GET /api/accounts/{accountNumber}/balance
     */
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<ApiResponse> getBalance(@PathVariable String accountNumber) {
        var balance = accountService.getBalance(accountNumber);

        return ResponseEntity
                .ok(ApiResponse.success("Balance retrieved successfully",
                        Map.of("accountNumber", accountNumber, "balance", balance)));
    }
}
