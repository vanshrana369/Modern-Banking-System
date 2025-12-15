// Feature branch: future enhancement for minimum balance validation
package com.banking.service;

import com.banking.dto.AccountRequest;
import com.banking.exception.ResourceNotFoundException;
import com.banking.model.Account;
import com.banking.model.User;
import com.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Service layer for Account-related business logic
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    /**
     * Create a new bank account
     */
    public Account createAccount(AccountRequest request) {
        // Validate user exists
        User user = userService.getUserById(request.getUserId());

        // Validate initial balance
        if (request.getInitialBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        // Create account
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setHolderName(request.getHolderName());
        account.setBalance(request.getInitialBalance());
        account.setPhone(request.getPhone());
        account.setAddress(request.getAddress());
        account.setDateOfBirth(request.getDateOfBirth());
        account.setUser(user);

        return accountRepository.save(account);
    }

    /**
     * Get all accounts for a specific user
     */
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    /**
     * Get account by account number
     */
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with number: " + accountNumber));
    }

    /**
     * Get account balance
     */
    public BigDecimal getBalance(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        return account.getBalance();
    }

    /**
     * Update account balance
     */
    public void updateBalance(Account account, BigDecimal newBalance) {
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    /**
     * Generate unique account number
     */
    private String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.format("%012d", new Random().nextLong() % 1000000000000L);
            accountNumber = accountNumber.replace("-", "1"); // Remove negative sign if any
        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}
