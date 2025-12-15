/**
 * Authentication JavaScript
 * Handles login and registration
 */

// Show alert message
function showAlert(message, type = 'success') {
    const alertDiv = document.getElementById('alertMessage');
    const alertText = document.getElementById('alertText');

    alertDiv.className = `alert alert-${type} alert-dismissible fade show mt-3`;
    alertText.textContent = message;
    alertDiv.style.display = 'block';

    // Auto-hide after 5 seconds
    setTimeout(() => {
        alertDiv.style.display = 'none';
    }, 5000);
}

// Handle Login Form
document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    try {
        const response = await fetch(API_ENDPOINTS.LOGIN, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (data.success) {
            // Save user data to localStorage
            localStorage.setItem('user', JSON.stringify(data.data));

            // Show success message
            showAlert('Login successful! Redirecting...', 'success');

            // Redirect to dashboard after 1 second
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);
        } else {
            showAlert(data.message || 'Login failed', 'danger');
        }
    } catch (error) {
        console.error('Login error:', error);
        showAlert('Error connecting to server. Please make sure the backend is running.', 'danger');
    }
});

// Handle Register Form
document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('registerUsername').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;

    try {
        const response = await fetch(API_ENDPOINTS.REGISTER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, email, password })
        });

        const data = await response.json();

        if (data.success) {
            showAlert('Registration successful! Please login.', 'success');

            // Clear form
            document.getElementById('registerForm').reset();

            // Switch to login tab after 2 seconds
            setTimeout(() => {
                document.getElementById('login-tab').click();
            }, 2000);
        } else {
            showAlert(data.message || 'Registration failed', 'danger');
        }
    } catch (error) {
        console.error('Registration error:', error);
        showAlert('Error connecting to server. Please make sure the backend is running.', 'danger');
    }
});
