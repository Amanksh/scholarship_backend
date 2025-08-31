package com.nsp.portal.controller;

import com.nsp.portal.dto.AuthResponse;
import com.nsp.portal.dto.StudentRegistrationRequest;
import com.nsp.portal.dto.InstituteRegistrationRequest;
import com.nsp.portal.dto.LoginRequest;
import com.nsp.portal.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * Registers a new student user.
     * 
     * @param request the student registration request
     * @return ResponseEntity with registration result
     */
    @Operation(summary = "Register Student", description = "Registers a new student user with profile")
    @PostMapping("/register/student")
    public ResponseEntity<AuthResponse> registerStudent(@Valid @RequestBody StudentRegistrationRequest request) {
        try {
            Object result = authService.registerStudent(request);
            
            if (result instanceof AuthResponse) {
                AuthResponse response = (AuthResponse) result;
                if (response.getMessage().contains("successfully")) {
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body(response);
                }
            } else {
                return ResponseEntity.badRequest().body(new AuthResponse("Registration failed"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new AuthResponse("Registration failed: " + e.getMessage()));
        }
    }
    
    /**
     * Submits an institute registration request.
     * 
     * @param request the institute registration request
     * @return ResponseEntity with registration result
     */
    @Operation(summary = "Register Institute", description = "Submits an institute registration request")
    @PostMapping("/register/institute")
    public ResponseEntity<AuthResponse> registerInstitute(@Valid @RequestBody InstituteRegistrationRequest request) {
        try {
            Object result = authService.registerInstitute(request);
            
            if (result instanceof AuthResponse) {
                AuthResponse response = (AuthResponse) result;
                if (response.getMessage().contains("successfully")) {
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body(response);
                }
            } else {
                return ResponseEntity.badRequest().body(new AuthResponse("Registration failed"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new AuthResponse("Registration failed: " + e.getMessage()));
        }
    }
    
    /**
     * Authenticates any user and returns a JWT token.
     * 
     * @param request the login request
     * @return ResponseEntity with JWT token and user details
     */
    @Operation(summary = "User Login", description = "Authenticates user and returns JWT token")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Object result = authService.login(request);
            
            if (result instanceof AuthResponse) {
                AuthResponse response = (AuthResponse) result;
                if (response.getMessage().contains("successful")) {
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body(response);
                }
            } else {
                return ResponseEntity.badRequest().body(new AuthResponse("Login failed"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new AuthResponse("Login failed: " + e.getMessage()));
        }
    }
}
