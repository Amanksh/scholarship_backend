package com.nsp.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Student Controller for handling all student-specific operations.
 * 
 * DEVELOPER 2 TASK: Implement the following endpoints:
 * 1. GET /api/schemes - Fetches all available scholarship schemes
 * 2. POST /api/applications - Submits a new scholarship application (multipart for documents)
 * 3. GET /api/applications - Gets all applications submitted by the logged-in student
 * 4. GET /api/student/profile - Gets the logged-in student's profile
 * 5. PUT /api/student/profile - Updates the student's profile
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
public class StudentController {
    
    @Autowired
    private com.nsp.portal.service.StudentService studentService;
    
    /**
     * Fetches all available scholarship schemes.
     * 
     * TODO: DEVELOPER 2 - Implement this method:
     * 1. Call studentService.getAllSchemes()
     * 2. Return list of available schemes
     * 3. Handle any exceptions gracefully
     * 
     * @return ResponseEntity with list of scholarship schemes
     */
    @GetMapping("/schemes")
    public ResponseEntity<?> getAllSchemes() {
        // TODO: Implement scheme retrieval logic
        // 1. Call service method
        // 2. Return schemes list
        // 3. Handle exceptions
        
        return ResponseEntity.ok("Get all schemes endpoint - TODO: Implement");
    }
    
    /**
     * Submits a new scholarship application with document uploads.
     * 
     * TODO: DEVELOPER 2 - Implement this method:
     * 1. Validate the application request
     * 2. Handle multiple document uploads
     * 3. Call studentService.submitApplication()
     * 4. Return success response with application details
     * 
     * @param applicationData the application form data
     * @param documents the uploaded documents
     * @return ResponseEntity with application submission result
     */
    @PostMapping("/applications")
    public ResponseEntity<?> submitApplication(
            @RequestPart("application") String applicationData,
            @RequestPart("documents") MultipartFile[] documents) {
        // TODO: Implement application submission logic
        // 1. Parse application data
        // 2. Handle document uploads
        // 3. Submit application
        // 4. Return response
        
        return ResponseEntity.ok("Submit application endpoint - TODO: Implement");
    }
    
    /**
     * Gets all applications submitted by the logged-in student.
     * 
     * TODO: DEVELOPER 2 - Implement this method:
     * 1. Get current user ID from security context
     * 2. Call studentService.getStudentApplications()
     * 3. Return list of student's applications
     * 
     * @return ResponseEntity with list of student applications
     */
    @GetMapping("/applications")
    public ResponseEntity<?> getStudentApplications() {
        // TODO: Implement application retrieval logic
        // 1. Get current user ID
        // 2. Call service method
        // 3. Return applications list
        
        return ResponseEntity.ok("Get student applications endpoint - TODO: Implement");
    }
    
    /**
     * Gets the logged-in student's profile.
     * 
     * TODO: DEVELOPER 2 - Implement this method:
     * 1. Get current user ID from security context
     * 2. Call studentService.getStudentProfile()
     * 3. Return student profile information
     * 
     * @return ResponseEntity with student profile
     */
    @GetMapping("/student/profile")
    public ResponseEntity<?> getStudentProfile() {
        // TODO: Implement profile retrieval logic
        // 1. Get current user ID
        // 2. Call service method
        // 3. Return profile data
        
        return ResponseEntity.ok("Get student profile endpoint - TODO: Implement");
    }
    
    /**
     * Updates the student's profile.
     * 
     * TODO: DEVELOPER 2 - Implement this method:
     * 1. Validate the update request
     * 2. Get current user ID from security context
     * 3. Call studentService.updateStudentProfile()
     * 4. Return updated profile information
     * 
     * @param profileUpdate the profile update request
     * @return ResponseEntity with updated profile
     */
    @PutMapping("/student/profile")
    public ResponseEntity<?> updateStudentProfile(@RequestBody Object profileUpdate) {
        // TODO: Implement profile update logic
        // 1. Validate request
        // 2. Get current user ID
        // 3. Update profile
        // 4. Return updated data
        
        return ResponseEntity.ok("Update student profile endpoint - TODO: Implement");
    }
}
