package com.nsp.portal.service.impl;

import com.nsp.portal.dto.StudentRegistrationRequest;
import com.nsp.portal.dto.InstituteRegistrationRequest;
import com.nsp.portal.dto.LoginRequest;
import com.nsp.portal.dto.AuthResponse;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * Registers a new student user.
     * 
     * @param request the student registration request
     * @return registration result
     */
    @Override
    @Transactional
    public Object registerStudent(StudentRegistrationRequest request) {
        try {
            // Check if email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse("Email already registered");
            }
            
            // Check if Aadhar number already exists
            if (studentProfileRepository.existsByAadharNumber(request.getAadharNumber())) {
                return new AuthResponse("Aadhar number already registered");
            }
            
            // Check if mobile number already exists
            if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
                return new AuthResponse("Mobile number already registered");
            }
            
            // Hash the password
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            
            // Create new User entity with STUDENT role
            User user = new User(
                request.getEmail(),
                hashedPassword,
                request.getMobileNumber(),
                Role.STUDENT
            );
            
            // Save the User entity
            User savedUser = userRepository.save(user);
            
            // Create new StudentProfile entity linked to the User
            StudentProfile studentProfile = new StudentProfile(
                savedUser,
                request.getName(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getDomicileState(),
                request.getAadharNumber()
            );
            
            // Set additional optional fields
            studentProfile.setFatherName(request.getFatherName());
            studentProfile.setMotherName(request.getMotherName());
            studentProfile.setCategory(request.getCategory());
            studentProfile.setReligion(request.getReligion());
            studentProfile.setAddress(request.getAddress());
            studentProfile.setDistrict(request.getDistrict());
            studentProfile.setPincode(request.getPincode());
            
            // Save the StudentProfile entity
            studentProfileRepository.save(studentProfile);
            
            // Return success response
            return new AuthResponse(
                "Student registered successfully",
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                request.getName()
            );
            
        } catch (Exception e) {
            return new AuthResponse("Registration failed: " + e.getMessage());
        }
    }
    
    /**
     * Registers a new institute user.
     * 
     * @param request the institute registration request
     * @return registration result
     */
    @Override
    @Transactional
    public Object registerInstitute(InstituteRegistrationRequest request) {
        try {
            // Check if email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse("Email already registered");
            }
            
            // Check if institute code already exists
            if (instituteProfileRepository.existsByInstituteCode(request.getInstituteCode())) {
                return new AuthResponse("Institute code already registered");
            }
            
            // Check if mobile number already exists
            if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
                return new AuthResponse("Mobile number already registered");
            }
            
            // Hash the password
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            
            // Create new User entity with INSTITUTE role
            User user = new User(
                request.getEmail(),
                hashedPassword,
                request.getMobileNumber(),
                Role.INSTITUTE
            );
            
            // Save the User entity
            User savedUser = userRepository.save(user);
            
            // Create new InstituteProfile entity (not approved yet)
            InstituteProfile instituteProfile = new InstituteProfile(
                savedUser,
                request.getInstituteName(),
                request.getInstituteCode()
            );
            
            // Set additional optional fields
            instituteProfile.setDiseCode(request.getDiseCode());
            instituteProfile.setAddress(request.getAddress());
            instituteProfile.setDistrict(request.getDistrict());
            instituteProfile.setState(request.getState());
            instituteProfile.setPincode(request.getPincode());
            instituteProfile.setContactPersonName(request.getContactPersonName());
            instituteProfile.setContactPersonMobile(request.getContactPersonMobile());
            instituteProfile.setContactPersonEmail(request.getContactPersonEmail());
            instituteProfile.setInstituteType(request.getInstituteType());
            instituteProfile.setAffiliationBody(request.getAffiliationBody());
            instituteProfile.setEstablishmentYear(request.getEstablishmentYear());
            
            // Save the InstituteProfile entity
            instituteProfileRepository.save(instituteProfile);
            
            // Return success response with pending approval message
            return new AuthResponse(
                "Institute registration submitted successfully. Pending approval from administrators.",
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                request.getInstituteName()
            );
            
        } catch (Exception e) {
            return new AuthResponse("Registration failed: " + e.getMessage());
        }
    }
    
    /**
     * Authenticates a user and returns JWT token.
     * 
     * @param request the login request
     * @return authentication result with JWT token
     */
    @Override
    public Object login(LoginRequest request) {
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            
            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            
            // Find the user in our database to get additional information
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);
            
            // Get user name based on role
            String userName = getUserName(user);
            
            // Return authentication response
            return new AuthResponse(
                token,
                "Login successful",
                user.getId(),
                user.getEmail(),
                user.getRole(),
                userName
            );
            
        } catch (Exception e) {
            return new AuthResponse("Invalid credentials");
        }
    }
    
    /**
     * Helper method to get user name based on role
     */
    private String getUserName(User user) {
        try {
            if (user.getRole() == Role.STUDENT) {
                StudentProfile profile = studentProfileRepository.findByUserId(user.getId())
                    .orElse(null);
                return profile != null ? profile.getName() : "Student";
            } else if (user.getRole() == Role.INSTITUTE) {
                InstituteProfile profile = instituteProfileRepository.findByUserId(user.getId())
                    .orElse(null);
                return profile != null ? profile.getInstituteName() : "Institute";
            } else {
                return user.getEmail();
            }
        } catch (Exception e) {
            return user.getEmail();
        }
    }
}
