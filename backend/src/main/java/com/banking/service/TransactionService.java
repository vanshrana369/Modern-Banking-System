package com.banking.service;

import com.banking.dto.TransactionRequest;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service layer for Transaction-related business logic
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Deposit money into an account
     */
    @Transactional
    public Transaction deposit(TransactionRequest request) {
        // Validate amount
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        // Get account
        Account account = accountService.getAccountByNumber(request.getAccountNumber());

        // Update balance
        BigDecimal newBalance = account.getBalance().add(request.getAmount());
        accountService.updateBalance(account, newBalance);

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transaction.setAmount(request.getAmount());
        transaction.setToAccount(request.getAccountNumber());
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
        transaction.setDescription(request.getDescription() != null ? request.getDescription() : "Deposit");

        return transactionRepository.save(transaction);
    }

    /**
     * Withdraw money from an account
     */
    @Transactional
    public Transaction withdraw(TransactionRequest request) {
        // Validate amount
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        // Get account
        Account account = accountService.getAccountByNumber(request.getAccountNumber());

        // Check sufficient balance
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            Transaction failedTransaction = new Transaction();
            failedTransaction.setType(Transaction.TransactionType.WITHDRAW);
            failedTransaction.setAmount(request.getAmount());
            failedTransaction.setFromAccount(request.getAccountNumber());
            failedTransaction.setStatus(Transaction.TransactionStatus.FAILED);
            failedTransaction.setDescription("Insufficient balance");
            transactionRepository.save(failedTransaction);

            throw new IllegalArgumentException("Insufficient balance");
        }

        // Update balance
        BigDecimal newBalance = account.getBalance().subtract(request.getAmount());
        accountService.updateBalance(account, newBalance);

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setType(Transaction.TransactionType.WITHDRAW);
        transaction.setAmount(request.getAmount());
        transaction.setFromAccount(request.getAccountNumber());
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
        transaction.setDescription(request.getDescription() != null ? request.getDescription() : "Withdrawal");

        return transactionRepository.save(transaction);
    }

    /**
     * Transfer money between accounts
     */
    @Transactional
    public Transaction transfer(TransactionRequest request) {
        // Validate amount
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        // Validate destination account
        if (request.getToAccountNumber() == null || request.getToAccountNumber().isEmpty()) {
            throw new IllegalArgumentException("Destination account number is required");
        }

        // Get accounts
        Account fromAccount = accountService.getAccountByNumber(request.getAccountNumber());
        Account toAccount = accountService.getAccountByNumber(request.getToAccountNumber());

        // Check sufficient balance
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            Transaction failedTransaction = new Transaction();
            failedTransaction.setType(Transaction.TransactionType.TRANSFER);
            failedTransaction.setAmount(request.getAmount());
            failedTransaction.setFromAccount(request.getAccountNumber());
            failedTransaction.setToAccount(request.getToAccountNumber());
            failedTransaction.setStatus(Transaction.TransactionStatus.FAILED);
            failedTransaction.setDescription("Insufficient balance");
            transactionRepository.save(failedTransaction);

            throw new IllegalArgumentException("Insufficient balance");
        }

        // Update balances
        BigDecimal fromNewBalance = fromAccount.getBalance().subtract(request.getAmount());
        BigDecimal toNewBalance = toAccount.getBalance().add(request.getAmount());

        accountService.updateBalance(fromAccount, fromNewBalance);
        accountService.updateBalance(toAccount, toNewBalance);

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setType(Transaction.TransactionType.TRANSFER);
        transaction.setAmount(request.getAmount());
        transaction.setFromAccount(request.getAccountNumber());
        transaction.setToAccount(request.getToAccountNumber());
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
        transaction.setDescription(request.getDescription() != null ? request.getDescription() : "Transfer");

        return transactionRepository.save(transaction);
    }

    /**
     * Get transaction history for an account
     */
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByFromAccountOrToAccountOrderByTransactionDateDesc(
                accountNumber, accountNumber);
    }
}
