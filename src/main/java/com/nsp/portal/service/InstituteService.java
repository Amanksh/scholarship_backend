package com.nsp.portal.service;

/**
 * Service interface for institute operations.
 * 
 * DEVELOPER 3 TASK: Implement the following methods in InstituteServiceImpl:
 * 1. getPendingApplications() - Get pending applications for institute
 * 2. verifyApplication() - Verify and forward application to state
 * 3. rejectApplication() - Reject application with reason
 * 
 * This service is responsible for:
 * - Managing student applications at institute level
 * - Application verification workflow
 * - Application rejection handling
 * - Status updates and workflow progression
 */
public interface InstituteService {
    
    /**
     * Gets pending student applications for the institute.
     * 
     * @param instituteId the institute's user ID
     * @return list of pending applications
     */
    Object getPendingApplications(Long instituteId);
    
    /**
     * Verifies a student application and forwards it to state level.
     * 
     * @param applicationId the application ID to verify
     * @param instituteId the institute's user ID
     * @param verificationData the verification details
     * @return verification result
     */
    Object verifyApplication(Long applicationId, Long instituteId, Object verificationData);
    
    /**
     * Rejects a student's application.
     * 
     * @param applicationId the application ID to reject
     * @param instituteId the institute's user ID
     * @param rejectionData the rejection details and reason
     * @return rejection result
     */
    Object rejectApplication(Long applicationId, Long instituteId, Object rejectionData);
}
