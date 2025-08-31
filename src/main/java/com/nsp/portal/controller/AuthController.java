package com.nsp.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller for handling user registration and login.
 * 
 * DEVELOPER 1 TASK: Implement the following endpoints:
 * 1. POST /api/auth/register/student - Register a new student
 * 2. POST /api/auth/register/institute - Submit institute registration request
 * 3. POST /api/auth/login - Authenticate any user and return JWT
 * 
 * This controller is responsible for:
 * - Student registration with profile creation
 * - Institute registration (pending approval)
 * - User authentication and JWT token generation
 * - Input validation and error handling
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private com.nsp.portal.service.AuthService authService;
    
    /**
     * Registers a new student user.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Validate the registration request
     * 2. Check if email already exists
     * 3. Hash the password using BCrypt
     * 4. Create User entity with STUDENT role
     * 5. Create StudentProfile entity
     * 6. Save both entities
     * 7. Return success response with user details
     * 
     * @param request the student registration request
     * @return ResponseEntity with registration result
     */
    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody com.nsp.portal.dto.StudentRegistrationRequest request) {
        // TODO: Implement student registration logic
        // 1. Validate request
        // 2. Check email uniqueness
        // 3. Hash password
        // 4. Create user and profile
        // 5. Return response
        
        return ResponseEntity.ok("Student registration endpoint - TODO: Implement");
    }
    
    /**
     * Submits an institute registration request.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Validate the registration request
     * 2. Check if email already exists
     * 3. Hash the password using BCrypt
     * 4. Create User entity with INSTITUTE role
     * 5. Create InstituteProfile entity (not approved yet)
     * 6. Save both entities
     * 7. Return success response with pending approval message
     * 
     * @param request the institute registration request
     * @return ResponseEntity with registration result
     */
    @PostMapping("/register/institute")
    public ResponseEntity<?> registerInstitute(@RequestBody com.nsp.portal.dto.InstituteRegistrationRequest request) {
        // TODO: Implement institute registration logic
        // 1. Validate request
        // 2. Check email uniqueness
        // 3. Hash password
        // 4. Create user and profile (not approved)
        // 5. Return response
        
        return ResponseEntity.ok("Institute registration endpoint - TODO: Implement");
    }
    
    /**
     * Authenticates any user and returns a JWT token.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Validate the login request
     * 2. Authenticate user credentials
     * 3. Generate JWT token
     * 4. Return JWT token and user details
     * 
     * @param request the login request
     * @return ResponseEntity with JWT token and user details
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody com.nsp.portal.dto.LoginRequest request) {
        // TODO: Implement login logic
        // 1. Validate request
        // 2. Authenticate credentials
        // 3. Generate JWT
        // 4. Return token and user info
        
        return ResponseEntity.ok("Login endpoint - TODO: Implement");
    }
}
