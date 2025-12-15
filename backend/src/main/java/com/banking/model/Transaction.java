package com.banking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Transaction Entity - Represents a financial transaction
 */
@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "from_account", length = 20)
    private String fromAccount; // Used for transfers and withdrawals

    @Column(name = "to_account", length = 20)
    private String toAccount; // Used for transfers and deposits

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private String description;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }

    /**
     * Transaction Types
     */
    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

    /**
     * Transaction Status
     */
    public enum TransactionStatus {
        SUCCESS,
        FAILED,
        PENDING
    }
}
