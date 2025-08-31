package com.nsp.portal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * ApplicationDocument entity managing uploaded documents for each application.
 * Handles file storage paths and document type categorization.
 */
@Entity
@Table(name = "application_documents")
public class ApplicationDocument {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Application is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ScholarshipApplication application;
    
    @NotBlank(message = "Document type is required")
    @Column(nullable = false)
    private String documentType; // e.g., "AADHAR_CARD", "INCOME_CERTIFICATE", "CASTE_CERTIFICATE"
    
    @NotBlank(message = "File path is required")
    @Column(nullable = false)
    private String filePath; // Path to the stored file
    
    // Additional fields for document management
    @Column(nullable = false)
    private String originalFileName;
    
    @Column(nullable = false)
    private String fileExtension;
    
    @Column(nullable = false)
    private Long fileSize; // File size in bytes
    
    @Column(nullable = false)
    private LocalDateTime uploadDate;
    
    private String uploadRemarks;
    private boolean isVerified = false;
    private LocalDateTime verificationDate;
    private String verificationRemarks;
    
    // Default constructor
    public ApplicationDocument() {}
    
    // Constructor with required fields
    public ApplicationDocument(ScholarshipApplication application, String documentType, 
                             String filePath, String originalFileName, 
                             String fileExtension, Long fileSize) {
        this.application = application;
        this.documentType = documentType;
        this.filePath = filePath;
        this.originalFileName = originalFileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.uploadDate = LocalDateTime.now();
        this.isVerified = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public ScholarshipApplication getApplication() {
        return application;
    }
    
    public void setApplication(ScholarshipApplication application) {
        this.application = application;
    }
    
    public String getDocumentType() {
        return documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }
    
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
    
    public String getFileExtension() {
        return fileExtension;
    }
    
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public LocalDateTime getUploadDate() {
        return uploadDate;
    }
    
    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    public String getUploadRemarks() {
        return uploadRemarks;
    }
    
    public void setUploadRemarks(String uploadRemarks) {
        this.uploadRemarks = uploadRemarks;
    }
    
    public boolean isVerified() {
        return isVerified;
    }
    
    public void setVerified(boolean verified) {
        isVerified = verified;
    }
    
    public LocalDateTime getVerificationDate() {
        return verificationDate;
    }
    
    public void setVerificationDate(LocalDateTime verificationDate) {
        this.verificationDate = verificationDate;
    }
    
    public String getVerificationRemarks() {
        return verificationRemarks;
    }
    
    public void setVerificationRemarks(String verificationRemarks) {
        this.verificationRemarks = verificationRemarks;
    }
}
