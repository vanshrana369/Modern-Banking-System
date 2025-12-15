package com.banking.repository;

import com.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Transaction entity
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find all transactions for a specific account (sent or received)
     */
    List<Transaction> findByFromAccountOrToAccountOrderByTransactionDateDesc(
            String fromAccount, String toAccount);

    /**
     * Find all transactions by from account
     */
    List<Transaction> findByFromAccountOrderByTransactionDateDesc(String fromAccount);

    /**
     * Find all transactions by to account
     */
    List<Transaction> findByToAccountOrderByTransactionDateDesc(String toAccount);
}
