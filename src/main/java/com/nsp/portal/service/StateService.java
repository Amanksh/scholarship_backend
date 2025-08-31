package com.nsp.portal.service;

/**
 * Service interface for state officer operations.
 * 
 * DEVELOPER 4 TASK: Implement the following methods in StateServiceImpl:
 * 1. getPendingApplications() - Get pending applications for state verification
 * 2. approveApplication() - Approve and forward application to ministry
 * 3. rejectApplication() - Reject application at state level
 * 4. getPendingInstituteRequests() - Get pending institute registration requests
 * 5. approveInstituteRegistration() - Approve institute registration
 * 
 * This service is responsible for:
 * - Managing applications at state level
 * - Application approval/rejection workflow
 * - Institute registration approvals
 * - Workflow progression to ministry level
 */
public interface StateService {
    
    /**
     * Gets pending applications for state verification.
     * 
     * @param state the state for which to get applications
     * @return list of pending applications
     */
    Object getPendingApplications(String state);
    
    /**
     * Approves an application and forwards it to the Ministry.
     * 
     * @param applicationId the application ID to approve
     * @param stateOfficerId the state officer's user ID
     * @param approvalData the approval details
     * @return approval result
     */
    Object approveApplication(Long applicationId, Long stateOfficerId, Object approvalData);
    
    /**
     * Rejects an application at state level.
     * 
     * @param applicationId the application ID to reject
     * @param stateOfficerId the state officer's user ID
     * @param rejectionData the rejection details and reason
     * @return rejection result
     */
    Object rejectApplication(Long applicationId, Long stateOfficerId, Object rejectionData);
    
    /**
     * Gets pending institute registration requests for the state.
     * 
     * @param state the state for which to get requests
     * @return list of pending institute requests
     */
    Object getPendingInstituteRequests(String state);
    
    /**
     * Approves an institute registration request.
     * 
     * @param instituteId the institute ID to approve
     * @param stateOfficerId the state officer's user ID
     * @param approvalData the approval details
     * @return approval result
     */
    Object approveInstituteRegistration(Long instituteId, Long stateOfficerId, Object approvalData);
}
