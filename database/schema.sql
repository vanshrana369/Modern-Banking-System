-- ============================================
-- Modern Banking System - MySQL Database Schema
-- ============================================

-- Create database
CREATE DATABASE IF NOT EXISTS banking_system;
USE banking_system;

-- ============================================
-- Users Table
-- ============================================
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Accounts Table
-- ============================================
CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    holder_name VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    phone VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_account_number (account_number),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Transactions Table
-- ============================================
CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('DEPOSIT', 'WITHDRAW', 'TRANSFER') NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    from_account VARCHAR(20),
    to_account VARCHAR(20),
    status ENUM('SUCCESS', 'FAILED', 'PENDING') NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    INDEX idx_from_account (from_account),
    INDEX idx_to_account (to_account),
    INDEX idx_transaction_date (transaction_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Sample Data (Optional - for testing)
-- ============================================

-- Insert sample user
INSERT INTO users (username, email, password) 
VALUES ('testuser', 'test@example.com', 'password123');

-- Insert sample account
INSERT INTO accounts (account_number, holder_name, balance, phone, address, date_of_birth, user_id)
VALUES ('123456789012', 'John Doe', 1000.00, '1234567890', '123 Main St', '1990-01-01', 1);

-- Insert sample transactions
INSERT INTO transactions (type, amount, to_account, status, description)
VALUES ('DEPOSIT', 500.00, '123456789012', 'SUCCESS', 'Initial deposit');

INSERT INTO transactions (type, amount, from_account, status, description)
VALUES ('WITHDRAW', 200.00, '123456789012', 'SUCCESS', 'ATM withdrawal');
