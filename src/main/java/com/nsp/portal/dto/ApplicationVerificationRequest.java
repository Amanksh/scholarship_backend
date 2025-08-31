package com.nsp.portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for application verification and rejection requests.
 * 
 * This DTO is used by institutes to verify or reject student applications
 * with appropriate comments and verification details.
 */
public class ApplicationVerificationRequest {
    
    @NotBlank(message = "Verification comments are required")
    private String verificationComments;
    
    @NotNull(message = "Verification status is required")
    private Boolean isVerified;
    
    private String verificationRemarks;
    private String documentVerificationStatus;
    private String eligibilityVerificationStatus;
    private String academicVerificationStatus;
    
    // Default constructor
    public ApplicationVerificationRequest() {}
    
    // Constructor with required fields
    public ApplicationVerificationRequest(String verificationComments, Boolean isVerified) {
        this.verificationComments = verificationComments;
        this.isVerified = isVerified;
    }
    
    // Constructor with all fields
    public ApplicationVerificationRequest(String verificationComments, Boolean isVerified, 
                                        String verificationRemarks, String documentVerificationStatus, 
                                        String eligibilityVerificationStatus, String academicVerificationStatus) {
        this.verificationComments = verificationComments;
        this.isVerified = isVerified;
        this.verificationRemarks = verificationRemarks;
        this.documentVerificationStatus = documentVerificationStatus;
        this.eligibilityVerificationStatus = eligibilityVerificationStatus;
        this.academicVerificationStatus = academicVerificationStatus;
    }
    
    // Getters and Setters
    public String getVerificationComments() {
        return verificationComments;
    }
    
    public void setVerificationComments(String verificationComments) {
        this.verificationComments = verificationComments;
    }
    
    public Boolean getIsVerified() {
        return isVerified;
    }
    
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    public String getVerificationRemarks() {
        return verificationRemarks;
    }
    
    public void setVerificationRemarks(String verificationRemarks) {
        this.verificationRemarks = verificationRemarks;
    }
    
    public String getDocumentVerificationStatus() {
        return documentVerificationStatus;
    }
    
    public void setDocumentVerificationStatus(String documentVerificationStatus) {
        this.documentVerificationStatus = documentVerificationStatus;
    }
    
    public String getEligibilityVerificationStatus() {
        return eligibilityVerificationStatus;
    }
    
    public void setEligibilityVerificationStatus(String eligibilityVerificationStatus) {
        this.eligibilityVerificationStatus = eligibilityVerificationStatus;
    }
    
    public String getAcademicVerificationStatus() {
        return academicVerificationStatus;
    }
    
    public void setAcademicVerificationStatus(String academicVerificationStatus) {
        this.academicVerificationStatus = academicVerificationStatus;
    }
}
