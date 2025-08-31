package com.nsp.portal.controller;

import com.nsp.portal.dto.InstituteProfileUpdateRequest;
import com.nsp.portal.dto.ApplicationVerificationRequest;
import com.nsp.portal.dto.InstituteResponse;
import com.nsp.portal.service.InstituteService;
import com.nsp.portal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for institute-specific operations.
 * 
 * This controller handles:
 * - Institute profile management
 * - Student application verification
 * - Institute dashboard operations
 * - Application approval/rejection workflow
 * 
 * Access: INSTITUTE role required for all endpoints
 */
@RestController
@RequestMapping("/api/institute")
@Tag(name = "Institute Operations", description = "Institute-specific operations and application verification")
@PreAuthorize("hasRole('INSTITUTE')")
public class InstituteController {
    
    @Autowired
    private InstituteService instituteService;
    
    @Autowired
    private UserService userService;
    
    /**
     * Gets the institute's profile information.
     * 
     * @return institute profile details
     */
    @Operation(summary = "Get Institute Profile", description = "Retrieves the logged-in institute's profile information")
    @GetMapping("/profile")
    public ResponseEntity<Object> getInstituteProfile() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object profile = instituteService.getInstituteProfile(instituteId);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving profile: " + e.getMessage()));
        }
    }
    
    /**
     * Updates the institute's profile information.
     * 
     * @param profileUpdate the profile update request
     * @return updated profile information
     */
    @Operation(summary = "Update Institute Profile", description = "Updates the logged-in institute's profile information")
    @PutMapping("/profile")
    public ResponseEntity<Object> updateInstituteProfile(@RequestBody InstituteProfileUpdateRequest profileUpdate) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object updatedProfile = instituteService.updateInstituteProfile(instituteId, profileUpdate);
            return ResponseEntity.ok(updatedProfile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error updating profile: " + e.getMessage()));
        }
    }
    
    /**
     * Gets all pending applications for the institute to verify.
     * 
     * @return list of pending applications
     */
    @Operation(summary = "Get Pending Applications", description = "Retrieves all pending applications for institute verification")
    @GetMapping("/applications/pending")
    public ResponseEntity<Object> getPendingApplications() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object applications = instituteService.getPendingApplications(instituteId);
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving pending applications: " + e.getMessage()));
        }
    }
    
    /**
     * Gets all applications for the institute (pending, verified, rejected).
     * 
     * @param status optional status filter
     * @return list of applications
     */
    @Operation(summary = "Get All Applications", description = "Retrieves all applications for the institute with optional status filtering")
    @GetMapping("/applications")
    public ResponseEntity<Object> getAllApplications(
            @Parameter(description = "Application status filter (optional)")
            @RequestParam(required = false) String status) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object applications = instituteService.getAllApplications(instituteId, status);
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving applications: " + e.getMessage()));
        }
    }
    
    /**
     * Gets a specific application by ID.
     * 
     * @param applicationId the application ID
     * @return application details
     */
    @Operation(summary = "Get Application by ID", description = "Retrieves a specific application by its ID")
    @GetMapping("/applications/{applicationId}")
    public ResponseEntity<Object> getApplicationById(
            @Parameter(description = "Application ID", required = true)
            @PathVariable Long applicationId) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object application = instituteService.getApplicationById(instituteId, applicationId);
            if (application != null) {
                return ResponseEntity.ok(application);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving application: " + e.getMessage()));
        }
    }
    
    /**
     * Verifies a student's scholarship application.
     * 
     * @param applicationId the application ID
     * @param verificationRequest the verification details
     * @return verification result
     */
    @Operation(summary = "Verify Application", description = "Verifies a student's scholarship application")
    @PostMapping("/applications/{applicationId}/verify")
    public ResponseEntity<Object> verifyApplication(
            @Parameter(description = "Application ID", required = true)
            @PathVariable Long applicationId,
            @RequestBody ApplicationVerificationRequest verificationRequest) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object result = instituteService.verifyApplication(instituteId, applicationId, verificationRequest);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error verifying application: " + e.getMessage()));
        }
    }
    
    /**
     * Rejects a student's scholarship application.
     * 
     * @param applicationId the application ID
     * @param rejectionRequest the rejection details
     * @return rejection result
     */
    @Operation(summary = "Reject Application", description = "Rejects a student's scholarship application")
    @PostMapping("/applications/{applicationId}/reject")
    public ResponseEntity<Object> rejectApplication(
            @Parameter(description = "Application ID", required = true)
            @PathVariable Long applicationId,
            @RequestBody ApplicationVerificationRequest rejectionRequest) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object result = instituteService.rejectApplication(instituteId, applicationId, rejectionRequest);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error rejecting application: " + e.getMessage()));
        }
    }
    
    /**
     * Gets institute statistics and dashboard data.
     * 
     * @return institute dashboard statistics
     */
    @Operation(summary = "Get Institute Dashboard", description = "Retrieves institute dashboard statistics and summary data")
    @GetMapping("/dashboard")
    public ResponseEntity<Object> getDashboard() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object dashboard = instituteService.getDashboard(instituteId);
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving dashboard: " + e.getMessage()));
        }
    }
    
    /**
     * Gets all students registered with this institute.
     * 
     * @return list of registered students
     */
    @Operation(summary = "Get Registered Students", description = "Retrieves all students registered with this institute")
    @GetMapping("/students")
    public ResponseEntity<Object> getRegisteredStudents() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object students = instituteService.getRegisteredStudents(instituteId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving students: " + e.getMessage()));
        }
    }
    
    /**
     * Gets a specific student's details by ID.
     * 
     * @param studentId the student ID
     * @return student details
     */
    @Operation(summary = "Get Student Details", description = "Retrieves details of a specific student registered with this institute")
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Object> getStudentDetails(
            @Parameter(description = "Student ID", required = true)
            @PathVariable Long studentId) {
        try {
            Long instituteId = getCurrentInstituteId();
            Object student = instituteService.getStudentDetails(instituteId, studentId);
            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving student details: " + e.getMessage()));
        }
    }
    
    /**
     * Gets all scholarship schemes available for this institute.
     * 
     * @return list of available scholarship schemes
     */
    @Operation(summary = "Get Available Schemes", description = "Retrieves all scholarship schemes available for this institute")
    @GetMapping("/schemes")
    public ResponseEntity<Object> getAvailableSchemes() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object schemes = instituteService.getAvailableSchemes(instituteId);
            return ResponseEntity.ok(schemes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving schemes: " + e.getMessage()));
        }
    }
    
    /**
     * Gets institute registration status and approval information.
     * 
     * @return registration status details
     */
    @Operation(summary = "Get Registration Status", description = "Retrieves institute registration status and approval information")
    @GetMapping("/registration/status")
    public ResponseEntity<Object> getRegistrationStatus() {
        try {
            Long instituteId = getCurrentInstituteId();
            Object status = instituteService.getRegistrationStatus(instituteId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new InstituteResponse("Error retrieving registration status: " + e.getMessage()));
        }
    }
    
    /**
     * Helper method to get the current institute's user ID from the security context.
     * 
     * @return current institute user ID
     */
    private Long getCurrentInstituteId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserIdByEmail(email);
    }
}
