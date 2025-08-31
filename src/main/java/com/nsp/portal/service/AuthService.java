package com.nsp.portal.service;

import com.nsp.portal.dto.StudentRegistrationRequest;
import com.nsp.portal.dto.InstituteRegistrationRequest;
import com.nsp.portal.dto.LoginRequest;

/**
 * Service interface for authentication operations.
 * 
 * DEVELOPER 1 TASK: Implement the following methods in AuthServiceImpl:
 * 1. registerStudent() - Register a new student user
 * 2. registerInstitute() - Register a new institute user
 * 3. login() - Authenticate user and return JWT token
 * 
 * This service is responsible for:
 * - User registration (students and institutes)
 * - User authentication
 * - JWT token generation
 * - Password hashing and validation
 */
public interface AuthService {
    
    /**
     * Registers a new student user.
     * 
     * @param request the student registration request
     * @return registration result with user details
     */
    Object registerStudent(StudentRegistrationRequest request);
    
    /**
     * Registers a new institute user.
     * 
     * @param request the institute registration request
     * @return registration result with user details
     */
    Object registerInstitute(InstituteRegistrationRequest request);
    
    /**
     * Authenticates a user and returns JWT token.
     * 
     * @param request the login request
     * @return authentication result with JWT token
     */
    Object login(LoginRequest request);
}
