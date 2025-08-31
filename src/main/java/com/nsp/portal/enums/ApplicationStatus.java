package com.nsp.portal.enums;

/**
 * Enum representing the different statuses a scholarship application can have.
 * Tracks the progress of an application through the approval workflow.
 */
public enum ApplicationStatus {
    APPLIED,                        // Initial status when student submits application
    PENDING_INSTITUTE_VERIFICATION, // Waiting for institute to verify
    REJECTED_BY_INSTITUTE,         // Application rejected by institute
    PENDING_STATE_VERIFICATION,    // Forwarded to state for verification
    REJECTED_BY_STATE,             // Application rejected by state officer
    PENDING_MINISTRY_APPROVAL,     // Forwarded to ministry for final approval
    REJECTED_BY_MINISTRY,          // Application rejected by ministry
    GRANTED                         // Scholarship finally granted
}
