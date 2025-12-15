package com.banking.service;

import com.banking.dto.LoginRequest;
import com.banking.dto.RegisterRequest;
import com.banking.exception.ResourceNotFoundException;
import com.banking.model.User;
import com.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service layer for User-related business logic
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user
     */
    public User registerUser(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Hash the password (simple hashing - in production use BCrypt)
        user.setPassword(hashPassword(request.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Authenticate user login
     */
    public Map<String, Object> loginUser(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        // Verify password
        if (!verifyPassword(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Return user data (excluding password)
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());

        return response;
    }

    /**
     * Get user by ID
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    /**
     * Simple password hashing (use BCrypt in production)
     */
    private String hashPassword(String password) {
        // For simplicity, using direct storage
        // In production, use: BCryptPasswordEncoder encoder = new
        // BCryptPasswordEncoder();
        // return encoder.encode(password);
        return password; // SIMPLIFIED FOR DEMO - USE BCRYPT IN PRODUCTION
    }

    /**
     * Verify password
     */
    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        // In production: return encoder.matches(rawPassword, hashedPassword);
        return rawPassword.equals(hashedPassword); // SIMPLIFIED FOR DEMO
    }
}
