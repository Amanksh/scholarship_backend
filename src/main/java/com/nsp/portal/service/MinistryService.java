package com.nsp.portal.service;

/**
 * Service interface for ministry operations.
 * 
 * DEVELOPER 5 TASK: Implement the following methods in MinistryServiceImpl:
 * 1. getPendingApplications() - Get applications pending ministry approval
 * 2. grantScholarship() - Grant scholarship to approved application
 * 3. rejectApplication() - Reject application at ministry level
 * 4. getPendingInstituteRequests() - Get pending institute registration requests
 * 5. approveInstituteRegistration() - Approve institute registration
 * 
 * This service is responsible for:
 * - Final approval of scholarship applications
 * - Granting scholarships to approved students
 * - Final approval of institute registrations
 * - Managing the final stage of application workflow
 */
public interface MinistryService {
    
    /**
     * Gets applications pending ministry approval.
     * 
     * @return list of applications pending ministry approval
     */
    Object getPendingApplications();
    
    /**
     * Grants the scholarship to an approved application.
     * 
     * @param applicationId the application ID to grant
     * @param ministryOfficerId the ministry officer's user ID
     * @param grantData the grant details
     * @return grant result
     */
    Object grantScholarship(Long applicationId, Long ministryOfficerId, Object grantData);
    
    /**
     * Rejects the final application at ministry level.
     * 
     * @param applicationId the application ID to reject
     * @param ministryOfficerId the ministry officer's user ID
     * @param rejectionData the rejection details and reason
     * @return rejection result
     */
    Object rejectApplication(Long applicationId, Long ministryOfficerId, Object rejectionData);
    
    /**
     * Gets pending institute registration requests.
     * 
     * @return list of pending institute requests
     */
    Object getPendingInstituteRequests();
    
    /**
     * Approves an institute's registration, activating their account.
     * 
     * @param instituteId the institute ID to approve
     * @param ministryOfficerId the ministry officer's user ID
     * @param approvalData the approval details
     * @return approval result
     */
    Object approveInstituteRegistration(Long instituteId, Long ministryOfficerId, Object approvalData);
}
