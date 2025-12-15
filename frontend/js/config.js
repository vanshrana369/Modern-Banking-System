/**
 * API Configuration
 * Update this URL based on your backend server
 */

const API_BASE_URL = 'http://localhost:8080/api';

const API_ENDPOINTS = {
    // Auth endpoints
    REGISTER: `${API_BASE_URL}/auth/register`,
    LOGIN: `${API_BASE_URL}/auth/login`,
    
    // Account endpoints
    CREATE_ACCOUNT: `${API_BASE_URL}/accounts/create`,
    GET_USER_ACCOUNTS: (userId) => `${API_BASE_URL}/accounts/user/${userId}`,
    GET_ACCOUNT: (accountNumber) => `${API_BASE_URL}/accounts/${accountNumber}`,
    GET_BALANCE: (accountNumber) => `${API_BASE_URL}/accounts/${accountNumber}/balance`,
    
    // Transaction endpoints
    DEPOSIT: `${API_BASE_URL}/transactions/deposit`,
    WITHDRAW: `${API_BASE_URL}/transactions/withdraw`,
    TRANSFER: `${API_BASE_URL}/transactions/transfer`,
    GET_TRANSACTIONS: (accountNumber) => `${API_BASE_URL}/transactions/account/${accountNumber}`
};
