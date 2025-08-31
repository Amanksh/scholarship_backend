package com.nsp.portal.dto;

import com.nsp.portal.enums.Role;

/**
 * DTO for authentication responses.
 * Contains JWT token and user information after successful authentication.
 */
public class AuthResponse {
    
    private String token;
    private String message;
    private Long userId;
    private String email;
    private Role role;
    private String name;
    
    // Default constructor
    public AuthResponse() {}
    
    // Constructor for successful authentication
    public AuthResponse(String token, String message, Long userId, String email, Role role, String name) {
        this.token = token;
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.name = name;
    }
    
    // Constructor for registration success
    public AuthResponse(String message, Long userId, String email, Role role, String name) {
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.name = name;
    }
    
    // Constructor for error messages
    public AuthResponse(String message) {
        this.message = message;
    }
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
