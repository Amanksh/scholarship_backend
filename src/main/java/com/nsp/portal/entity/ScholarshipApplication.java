package com.nsp.portal.entity;

import com.nsp.portal.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * ScholarshipApplication entity linking a student to a scheme and tracking its progress.
 * This is the central entity that manages the entire application workflow.
 */
@Entity
@Table(name = "scholarship_applications")
public class ScholarshipApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;
    
    @NotNull(message = "Scholarship scheme is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheme_id", nullable = false)
    private ScholarshipScheme scheme;
    
    @NotNull(message = "Application date is required")
    @Column(nullable = false)
    private LocalDate applicationDate;
    
    @NotNull(message = "Family annual income is required")
    @Positive(message = "Family annual income must be positive")
    @Column(nullable = false)
    private BigDecimal familyAnnualIncome;
    
    @NotNull(message = "Application status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.APPLIED;
    
    // Additional fields for comprehensive application information
    private String academicYear;
    private String currentClass;
    private String previousClassPercentage;
    private String currentInstituteName;
    private String currentInstituteCode;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String accountHolderName;
    
    // Application workflow tracking
    private LocalDate instituteVerificationDate;
    private String instituteVerificationRemarks;
    private LocalDate stateVerificationDate;
    private String stateVerificationRemarks;
    private LocalDate ministryApprovalDate;
    private String ministryApprovalRemarks;
    
    // Document references
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationDocument> documents;
    
    // Default constructor
    public ScholarshipApplication() {}
    
    // Constructor with required fields
    public ScholarshipApplication(StudentProfile student, ScholarshipScheme scheme, 
                               LocalDate applicationDate, BigDecimal familyAnnualIncome) {
        this.student = student;
        this.scheme = scheme;
        this.applicationDate = applicationDate;
        this.familyAnnualIncome = familyAnnualIncome;
        this.status = ApplicationStatus.APPLIED;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public StudentProfile getStudent() {
        return student;
    }
    
    public void setStudent(StudentProfile student) {
        this.student = student;
    }
    
    public ScholarshipScheme getScheme() {
        return scheme;
    }
    
    public void setScheme(ScholarshipScheme scheme) {
        this.scheme = scheme;
    }
    
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public BigDecimal getFamilyAnnualIncome() {
        return familyAnnualIncome;
    }
    
    public void setFamilyAnnualIncome(BigDecimal familyAnnualIncome) {
        this.familyAnnualIncome = familyAnnualIncome;
    }
    
    public ApplicationStatus getStatus() {
        return status;
    }
    
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    
    // Additional getters and setters for extended fields
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    
    public String getCurrentClass() {
        return currentClass;
    }
    
    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }
    
    public String getPreviousClassPercentage() {
        return previousClassPercentage;
    }
    
    public void setPreviousClassPercentage(String previousClassPercentage) {
        this.previousClassPercentage = previousClassPercentage;
    }
    
    public String getCurrentInstituteName() {
        return currentInstituteName;
    }
    
    public void setCurrentInstituteName(String currentInstituteName) {
        this.currentInstituteName = currentInstituteName;
    }
    
    public String getCurrentInstituteCode() {
        return currentInstituteCode;
    }
    
    public void setCurrentInstituteCode(String currentInstituteCode) {
        this.currentInstituteCode = currentInstituteCode;
    }
    
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
    
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getIfscCode() {
        return ifscCode;
    }
    
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    
    public LocalDate getInstituteVerificationDate() {
        return instituteVerificationDate;
    }
    
    public void setInstituteVerificationDate(LocalDate instituteVerificationDate) {
        this.instituteVerificationDate = instituteVerificationDate;
    }
    
    public String getInstituteVerificationRemarks() {
        return instituteVerificationRemarks;
    }
    
    public void setInstituteVerificationRemarks(String instituteVerificationRemarks) {
        this.instituteVerificationRemarks = instituteVerificationRemarks;
    }
    
    public LocalDate getStateVerificationDate() {
        return stateVerificationDate;
    }
    
    public void setStateVerificationDate(LocalDate stateVerificationDate) {
        this.stateVerificationDate = stateVerificationDate;
    }
    
    public String getStateVerificationRemarks() {
        return stateVerificationRemarks;
    }
    
    public void setStateVerificationRemarks(String stateVerificationRemarks) {
        this.stateVerificationRemarks = stateVerificationRemarks;
    }
    
    public LocalDate getMinistryApprovalDate() {
        return ministryApprovalDate;
    }
    
    public void setMinistryApprovalDate(LocalDate ministryApprovalDate) {
        this.ministryApprovalDate = ministryApprovalDate;
    }
    
    public String getMinistryApprovalRemarks() {
        return ministryApprovalRemarks;
    }
    
    public void setMinistryApprovalRemarks(String ministryApprovalRemarks) {
        this.ministryApprovalRemarks = ministryApprovalRemarks;
    }
    
    public List<ApplicationDocument> getDocuments() {
        return documents;
    }
    
    public void setDocuments(List<ApplicationDocument> documents) {
        this.documents = documents;
    }
}
