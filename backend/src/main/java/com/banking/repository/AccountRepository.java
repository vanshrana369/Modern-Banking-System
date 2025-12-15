package com.banking.repository;

import com.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Account entity
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find account by account number
     */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Find all accounts belonging to a specific user
     */
    List<Account> findByUserId(Long userId);

    /**
     * Check if account number already exists
     */
    boolean existsByAccountNumber(String accountNumber);
}
