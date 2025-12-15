/**
 * Dashboard JavaScript
 * Handles all dashboard operations
 */

let currentUser = null;
let userAccounts = [];

// Initialize dashboard when page loads
document.addEventListener('DOMContentLoaded', () => {
    // Check if user is logged in
    const userData = localStorage.getItem('user');

    if (!userData) {
        window.location.href = 'index.html';
        return;
    }

    currentUser = JSON.parse(userData);

    // Display user info
    document.getElementById('userDisplay').textContent = currentUser.username;
    document.getElementById('welcomeUser').textContent = currentUser.username;

    // Load user accounts
    loadAccounts();
});

// Logout handler
document.getElementById('logoutBtn').addEventListener('click', () => {
    localStorage.removeItem('user');
    window.location.href = 'index.html';
});

// Load user accounts
async function loadAccounts() {
    try {
        const response = await fetch(API_ENDPOINTS.GET_USER_ACCOUNTS(currentUser.id));
        const data = await response.json();

        if (data.success) {
            userAccounts = data.data;
            displayAccounts();
            populateAccountDropdowns();
        }
    } catch (error) {
        console.error('Error loading accounts:', error);
        showToast('Error loading accounts', 'danger');
    }
}

// Display accounts
function displayAccounts() {
    const accountsList = document.getElementById('accountsList');

    if (userAccounts.length === 0) {
        accountsList.innerHTML = `
            <div class="col-12 text-center text-muted">
                <i class="fas fa-wallet fa-3x mb-3"></i>
                <p>No accounts yet. Create your first account!</p>
            </div>
        `;
        return;
    }

    accountsList.innerHTML = userAccounts.map(account => `
        <div class="col-md-4 mb-3">
            <div class="account-card">
                <div class="account-number">
                    <i class="fas fa-wallet me-2"></i>${account.accountNumber}
                </div>
                <div class="account-holder">
                    <i class="fas fa-user me-2"></i>${account.holderName}
                </div>
                <div class="account-balance">$${parseFloat(account.balance).toFixed(2)}</div>
                <div class="balance-label">Available Balance</div>
            </div>
        </div>
    `).join('');
}

// Populate account dropdowns
function populateAccountDropdowns() {
    const accountOptions = userAccounts.map(account =>
        `<option value="${account.accountNumber}">${account.accountNumber} - ${account.holderName}</option>`
    ).join('');

    document.getElementById('depositAccount').innerHTML =
        '<option value="">Choose account...</option>' + accountOptions;
    document.getElementById('withdrawAccount').innerHTML =
        '<option value="">Choose account...</option>' + accountOptions;
    document.getElementById('transferFromAccount').innerHTML =
        '<option value="">Choose account...</option>' + accountOptions;
    document.getElementById('accountFilter').innerHTML =
        '<option value="">Select an account to view transactions</option>' + accountOptions;
}

// Show toast notification
function showToast(message, type = 'success') {
    // Create toast element
    const toast = document.createElement('div');
    toast.className = `alert alert-${type} position-fixed top-0 end-0 m-3`;
    toast.style.zIndex = '9999';
    toast.textContent = message;

    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 3000);
}

// Create Account Form Handler
document.getElementById('createAccountForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const accountData = {
        holderName: document.getElementById('holderName').value,
        initialBalance: parseFloat(document.getElementById('initialBalance').value),
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        userId: currentUser.id
    };

    try {
        const response = await fetch(API_ENDPOINTS.CREATE_ACCOUNT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(accountData)
        });

        const data = await response.json();

        if (data.success) {
            showToast('Account created successfully!', 'success');

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('createAccountModal')).hide();

            // Reset form
            document.getElementById('createAccountForm').reset();

            // Reload accounts after a short delay to ensure backend persistence
            setTimeout(async () => {
                await loadAccounts();
            }, 500);
        } else {
            showToast(data.message || 'Failed to create account', 'danger');
        }
    } catch (error) {
        console.error('Error creating account:', error);
        showToast('Error creating account', 'danger');
    }
});

// Deposit Form Handler
document.getElementById('depositForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const transactionData = {
        accountNumber: document.getElementById('depositAccount').value,
        amount: parseFloat(document.getElementById('depositAmount').value),
        description: document.getElementById('depositDescription').value
    };

    try {
        const response = await fetch(API_ENDPOINTS.DEPOSIT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(transactionData)
        });

        const data = await response.json();

        if (data.success) {
            showToast('Deposit successful!', 'success');

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('depositModal')).hide();

            // Reset form
            document.getElementById('depositForm').reset();

            // Reload accounts
            await loadAccounts();
        } else {
            showToast(data.message || 'Deposit failed', 'danger');
        }
    } catch (error) {
        console.error('Error making deposit:', error);
        showToast('Error making deposit', 'danger');
    }
});

// Withdraw Form Handler
document.getElementById('withdrawForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const transactionData = {
        accountNumber: document.getElementById('withdrawAccount').value,
        amount: parseFloat(document.getElementById('withdrawAmount').value),
        description: document.getElementById('withdrawDescription').value
    };

    try {
        const response = await fetch(API_ENDPOINTS.WITHDRAW, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(transactionData)
        });

        const data = await response.json();

        if (data.success) {
            showToast('Withdrawal successful!', 'success');

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('withdrawModal')).hide();

            // Reset form
            document.getElementById('withdrawForm').reset();

            // Reload accounts
            await loadAccounts();
        } else {
            showToast(data.message || 'Withdrawal failed', 'danger');
        }
    } catch (error) {
        console.error('Error making withdrawal:', error);
        showToast('Error making withdrawal', 'danger');
    }
});

// Transfer Form Handler
document.getElementById('transferForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const transactionData = {
        accountNumber: document.getElementById('transferFromAccount').value,
        toAccountNumber: document.getElementById('transferToAccount').value,
        amount: parseFloat(document.getElementById('transferAmount').value),
        description: document.getElementById('transferDescription').value
    };

    try {
        const response = await fetch(API_ENDPOINTS.TRANSFER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(transactionData)
        });

        const data = await response.json();

        if (data.success) {
            showToast('Transfer successful!', 'success');

            // Close modal
            bootstrap.Modal.getInstance(document.getElementById('transferModal')).hide();

            // Reset form
            document.getElementById('transferForm').reset();

            // Reload accounts
            await loadAccounts();
        } else {
            showToast(data.message || 'Transfer failed', 'danger');
        }
    } catch (error) {
        console.error('Error making transfer:', error);
        showToast('Error making transfer', 'danger');
    }
});

// Account filter change handler
document.getElementById('accountFilter').addEventListener('change', async (e) => {
    const accountNumber = e.target.value;

    if (!accountNumber) {
        document.getElementById('transactionsList').innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-muted">
                    Select an account to view transactions
                </td>
            </tr>
        `;
        return;
    }

    await loadTransactions(accountNumber);
});

// Load transactions for an account
async function loadTransactions(accountNumber) {
    try {
        const response = await fetch(API_ENDPOINTS.GET_TRANSACTIONS(accountNumber));
        const data = await response.json();

        if (data.success) {
            displayTransactions(data.data);
        }
    } catch (error) {
        console.error('Error loading transactions:', error);
        showToast('Error loading transactions', 'danger');
    }
}

// Display transactions
function displayTransactions(transactions) {
    const transactionsList = document.getElementById('transactionsList');

    if (transactions.length === 0) {
        transactionsList.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-muted">
                    No transactions found for this account
                </td>
            </tr>
        `;
        return;
    }

    transactionsList.innerHTML = transactions.map(transaction => {
        const date = new Date(transaction.transactionDate).toLocaleString();
        const type = transaction.type;
        const status = transaction.status;

        return `
            <tr>
                <td>${date}</td>
                <td><span class="badge type-${type.toLowerCase()}">${type}</span></td>
                <td>${transaction.fromAccount || '-'}</td>
                <td>${transaction.toAccount || '-'}</td>
                <td>$${parseFloat(transaction.amount).toFixed(2)}</td>
                <td><span class="badge status-${status.toLowerCase()}">${status}</span></td>
            </tr>
            `;
    }).join('');
}
