package com.nsp.portal.controller;

import com.nsp.portal.dto.StudentProfileUpdateRequest;
import com.nsp.portal.dto.ScholarshipApplicationRequest;
import com.nsp.portal.dto.StudentResponse;
import com.nsp.portal.service.StudentService;
import com.nsp.portal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Student Controller for handling all student-specific operations.
 * 
 * This controller is responsible for:
 * - Viewing available scholarship schemes
 * - Submitting scholarship applications with document uploads
 * - Managing student profile information
 * - Tracking application status
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('STUDENT')")
@Tag(name = "Student Operations", description = "Student-specific operations and profile management")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UserService userService;
    
    /**
     * Fetches all available scholarship schemes.
     * 
     * @return ResponseEntity with list of scholarship schemes
     */
    @Operation(summary = "Get All Schemes", description = "Retrieves all available scholarship schemes")
    @GetMapping("/schemes")
    public ResponseEntity<?> getAllSchemes() {
        try {
            Object result = studentService.getAllSchemes();
            if (result instanceof List) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error retrieving schemes: " + e.getMessage());
        }
    }
    
    /**
     * Submits a new scholarship application with document uploads.
     * 
     * @param applicationData the application form data
     * @param documents the uploaded documents
     * @return ResponseEntity with application submission result
     */
    @Operation(summary = "Submit Application", description = "Submits a new scholarship application with documents")
    @PostMapping("/applications")
    public ResponseEntity<?> submitApplication(
            @RequestPart("application") @Valid ScholarshipApplicationRequest applicationData,
            @RequestPart(value = "documents", required = false) MultipartFile[] documents) {
        try {
            // Convert application data to JSON string for the service
            String applicationJson = convertToJson(applicationData);
            
            Object result = studentService.submitApplication(applicationJson, documents);
            if (result.toString().contains("success") || result.toString().contains("submitted")) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error submitting application: " + e.getMessage());
        }
    }
    
    /**
     * Gets all applications submitted by the logged-in student.
     * 
     * @return ResponseEntity with list of student applications
     */
    @Operation(summary = "Get Student Applications", description = "Retrieves all applications submitted by the logged-in student")
    @GetMapping("/applications")
    public ResponseEntity<?> getStudentApplications() {
        try {
            Long studentId = getCurrentUserId();
            if (studentId == null) {
                return ResponseEntity.badRequest().body("User not authenticated");
            }
            
            Object result = studentService.getStudentApplications(studentId);
            if (result instanceof List) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error retrieving applications: " + e.getMessage());
        }
    }
    
    /**
     * Gets the logged-in student's profile.
     * 
     * @return ResponseEntity with student profile
     */
    @Operation(summary = "Get Student Profile", description = "Retrieves the logged-in student's profile information")
    @GetMapping("/student/profile")
    public ResponseEntity<?> getStudentProfile() {
        try {
            Long studentId = getCurrentUserId();
            if (studentId == null) {
                return ResponseEntity.badRequest().body("User not authenticated");
            }
            
            Object result = studentService.getStudentProfile(studentId);
            if (result instanceof com.nsp.portal.entity.StudentProfile) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error retrieving profile: " + e.getMessage());
        }
    }
    
    /**
     * Updates the student's profile.
     * 
     * @param profileUpdate the profile update request
     * @return ResponseEntity with updated profile
     */
    @Operation(summary = "Update Student Profile", description = "Updates the logged-in student's profile information")
    @PutMapping("/student/profile")
    public ResponseEntity<?> updateStudentProfile(@Valid @RequestBody StudentProfileUpdateRequest profileUpdate) {
        try {
            Long studentId = getCurrentUserId();
            if (studentId == null) {
                return ResponseEntity.badRequest().body("User not authenticated");
            }
            
            Object result = studentService.updateStudentProfile(studentId, profileUpdate);
            if (result instanceof com.nsp.portal.entity.StudentProfile) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error updating profile: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to get current user ID from security context.
     */
    private Long getCurrentUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String email = authentication.getName();
                // Get user ID from email
                return userService.getUserIdByEmail(email);
            }
        } catch (Exception e) {
            // Log error if needed
        }
        return null;
    }
    
    /**
     * Helper method to convert DTO to JSON string.
     * This is a simple implementation - in production, use a proper JSON library.
     */
    private String convertToJson(ScholarshipApplicationRequest request) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"schemeId\":").append(request.getSchemeId());
        if (request.getAdditionalNotes() != null) {
            json.append(",\"additionalNotes\":\"").append(request.getAdditionalNotes()).append("\"");
        }
        if (request.getFamilyIncome() != null) {
            json.append(",\"familyIncome\":\"").append(request.getFamilyIncome()).append("\"");
        }
        if (request.getAcademicYear() != null) {
            json.append(",\"academicYear\":\"").append(request.getAcademicYear()).append("\"");
        }
        if (request.getCurrentSemester() != null) {
            json.append(",\"currentSemester\":\"").append(request.getCurrentSemester()).append("\"");
        }
        if (request.getCgpa() != null) {
            json.append(",\"cgpa\":\"").append(request.getCgpa()).append("\"");
        }
        if (request.getAttendancePercentage() != null) {
            json.append(",\"attendancePercentage\":\"").append(request.getAttendancePercentage()).append("\"");
        }
        json.append("}");
        return json.toString();
    }
}
