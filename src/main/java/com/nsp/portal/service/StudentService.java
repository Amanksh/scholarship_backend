package com.nsp.portal.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface for student operations.
 * 
 * DEVELOPER 2 TASK: Implement the following methods in StudentServiceImpl:
 * 1. getAllSchemes() - Retrieve all available scholarship schemes
 * 2. submitApplication() - Submit scholarship application with documents
 * 3. getStudentApplications() - Get all applications for a student
 * 4. getStudentProfile() - Get student profile information
 * 5. updateStudentProfile() - Update student profile
 * 
 * This service is responsible for:
 * - Managing scholarship scheme information
 * - Handling scholarship application submissions
 * - Managing student profile data
 * - Document upload and management
 */
public interface StudentService {
    
    /**
     * Retrieves all available scholarship schemes.
     * 
     * @return list of available scholarship schemes
     */
    Object getAllSchemes();
    
    /**
     * Submits a new scholarship application with documents.
     * 
     * @param applicationData the application form data
     * @param documents the uploaded documents
     * @return application submission result
     */
    Object submitApplication(String applicationData, MultipartFile[] documents);
    
    /**
     * Gets all applications submitted by a specific student.
     * 
     * @param studentId the student's user ID
     * @return list of student's applications
     */
    Object getStudentApplications(Long studentId);
    
    /**
     * Gets the profile information for a specific student.
     * 
     * @param studentId the student's user ID
     * @return student profile information
     */
    Object getStudentProfile(Long studentId);
    
    /**
     * Updates the profile information for a specific student.
     * 
     * @param studentId the student's user ID
     * @param profileUpdate the profile update data
     * @return updated profile information
     */
    Object updateStudentProfile(Long studentId, Object profileUpdate);
}
