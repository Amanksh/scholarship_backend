package com.nsp.portal.service.impl;

import com.nsp.portal.dto.StudentRegistrationRequest;
import com.nsp.portal.dto.InstituteRegistrationRequest;
import com.nsp.portal.dto.LoginRequest;
import com.nsp.portal.entity.User;
import com.nsp.portal.entity.StudentProfile;
import com.nsp.portal.entity.InstituteProfile;
import com.nsp.portal.enums.Role;
import com.nsp.portal.repository.UserRepository;
import com.nsp.portal.repository.StudentProfileRepository;
import com.nsp.portal.repository.InstituteProfileRepository;
import com.nsp.portal.security.JwtUtil;
import com.nsp.portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of AuthService for authentication operations.
 * 
 * DEVELOPER 1 TASK: Implement the following methods:
 * 1. registerStudent() - Complete student registration logic
 * 2. registerInstitute() - Complete institute registration logic
 * 3. login() - Complete user authentication logic
 * 
 * This service implementation is responsible for:
 * - Creating new user accounts with proper role assignment
 * - Creating associated profile entities (StudentProfile/InstituteProfile)
 * - Password hashing using BCrypt
 * - JWT token generation for successful login
 * - Input validation and business rule enforcement
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    @Autowired
    private InstituteProfileRepository instituteProfileRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    /**
     * Registers a new student user.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Check if email already exists in database
     * 2. Hash the password using passwordEncoder.encode()
     * 3. Create new User entity with STUDENT role
     * 4. Save the User entity
     * 5. Create new StudentProfile entity linked to the User
     * 6. Save the StudentProfile entity
     * 7. Return success response with user details
     * 
     * @param request the student registration request
     * @return registration result
     */
    @Override
    public Object registerStudent(StudentRegistrationRequest request) {
        // TODO: Implement student registration logic
        // 1. Check email uniqueness
        // 2. Hash password
        // 3. Create and save User
        // 4. Create and save StudentProfile
        // 5. Return response
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }
        
        // TODO: Complete the implementation
        // - Hash password
        // - Create User entity
        // - Create StudentProfile entity
        // - Save both entities
        // - Return success response
        
        return "Student registration - TODO: Complete implementation";
    }
    
    /**
     * Registers a new institute user.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Check if email already exists in database
     * 2. Check if institute code already exists
     * 3. Hash the password using passwordEncoder.encode()
     * 4. Create new User entity with INSTITUTE role
     * 5. Save the User entity
     * 6. Create new InstituteProfile entity (not approved yet)
     * 7. Save the InstituteProfile entity
     * 8. Return success response with pending approval message
     * 
     * @param request the institute registration request
     * @return registration result
     */
    @Override
    public Object registerInstitute(InstituteRegistrationRequest request) {
        // TODO: Implement institute registration logic
        // 1. Check email uniqueness
        // 2. Check institute code uniqueness
        // 3. Hash password
        // 4. Create and save User
        // 5. Create and save InstituteProfile (not approved)
        // 6. Return response
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }
        
        // Check if institute code already exists
        if (instituteProfileRepository.existsByInstituteCode(request.getInstituteCode())) {
            return "Institute code already registered";
        }
        
        // TODO: Complete the implementation
        // - Hash password
        // - Create User entity
        // - Create InstituteProfile entity (not approved)
        // - Save both entities
        // - Return success response
        
        return "Institute registration - TODO: Complete implementation";
    }
    
    /**
     * Authenticates a user and returns JWT token.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Authenticate user credentials using AuthenticationManager
     * 2. Load user details from UserDetailsService
     * 3. Generate JWT token using JwtUtil
     * 4. Return JWT token and user information
     * 
     * @param request the login request
     * @return authentication result with JWT token
     */
    @Override
    public Object login(LoginRequest request) {
        // TODO: Implement login logic
        // 1. Authenticate credentials
        // 2. Load user details
        // 3. Generate JWT token
        // 4. Return token and user info
        
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            
            // TODO: Complete the implementation
            // - Load user details
            // - Generate JWT token
            // - Return authentication response
            
            return "Login successful - TODO: Complete JWT generation";
            
        } catch (Exception e) {
            return "Invalid credentials";
        }
    }
}
