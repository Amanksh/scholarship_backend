package com.nsp.portal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ScholarshipScheme entity storing details about available scholarships.
 * Contains information about eligibility criteria and scholarship amounts.
 */
@Entity
@Table(name = "scholarship_schemes")
public class ScholarshipScheme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Scheme name is required")
    @Column(nullable = false, unique = true)
    private String schemeName;
    
    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @NotBlank(message = "Eligibility criteria is required")
    @Column(nullable = false, length = 2000)
    private String eligibilityCriteria;
    
    // Additional fields for comprehensive scheme information
    @NotNull(message = "Scholarship amount is required")
    @Positive(message = "Scholarship amount must be positive")
    @Column(nullable = false)
    private BigDecimal scholarshipAmount;
    
    @NotNull(message = "Application start date is required")
    @Column(nullable = false)
    private LocalDate applicationStartDate;
    
    @NotNull(message = "Application end date is required")
    @Column(nullable = false)
    private LocalDate applicationEndDate;
    
    @Column(nullable = false)
    private boolean isActive = true;
    
    private String category; // SC, ST, OBC, General, All
    private String gender; // Male, Female, All
    private String domicileState; // Specific state or All India
    private BigDecimal familyIncomeLimit; // Maximum family income to be eligible
    private String academicLevel; // Primary, Secondary, Higher Secondary, College
    private String subject; // Science, Arts, Commerce, All
    private String instituteType; // Government, Private, Aided, All
    
    // Default constructor
    public ScholarshipScheme() {}
    
    // Constructor with required fields
    public ScholarshipScheme(String schemeName, String description, String eligibilityCriteria, 
                           BigDecimal scholarshipAmount, LocalDate applicationStartDate, 
                           LocalDate applicationEndDate) {
        this.schemeName = schemeName;
        this.description = description;
        this.eligibilityCriteria = eligibilityCriteria;
        this.scholarshipAmount = scholarshipAmount;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
        this.isActive = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSchemeName() {
        return schemeName;
    }
    
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }
    
    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }
    
    public BigDecimal getScholarshipAmount() {
        return scholarshipAmount;
    }
    
    public void setScholarshipAmount(BigDecimal scholarshipAmount) {
        this.scholarshipAmount = scholarshipAmount;
    }
    
    public LocalDate getApplicationStartDate() {
        return applicationStartDate;
    }
    
    public void setApplicationStartDate(LocalDate applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }
    
    public LocalDate getApplicationEndDate() {
        return applicationEndDate;
    }
    
    public void setApplicationEndDate(LocalDate applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    // Additional getters and setters for extended fields
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDomicileState() {
        return domicileState;
    }
    
    public void setDomicileState(String domicileState) {
        this.domicileState = domicileState;
    }
    
    public BigDecimal getFamilyIncomeLimit() {
        return familyIncomeLimit;
    }
    
    public void setFamilyIncomeLimit(BigDecimal familyIncomeLimit) {
        this.familyIncomeLimit = familyIncomeLimit;
    }
    
    public String getAcademicLevel() {
        return academicLevel;
    }
    
    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getInstituteType() {
        return instituteType;
    }
    
    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }
}
