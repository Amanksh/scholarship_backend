package com.nsp.portal.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO for scholarship application submission.
 * Contains the basic information needed to submit an application.
 */
public class ScholarshipApplicationRequest {
    
    @NotNull(message = "Scheme ID is required")
    @Positive(message = "Scheme ID must be positive")
    private Long schemeId;
    
    private String additionalNotes;
    
    private String familyIncome;
    
    private String academicYear;
    
    private String currentSemester;
    
    private String cgpa;
    
    private String attendancePercentage;
    
    // Default constructor
    public ScholarshipApplicationRequest() {}
    
    // Constructor with required fields
    public ScholarshipApplicationRequest(Long schemeId) {
        this.schemeId = schemeId;
    }
    
    // Getters and Setters
    public Long getSchemeId() {
        return schemeId;
    }
    
    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }
    
    public String getAdditionalNotes() {
        return additionalNotes;
    }
    
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    
    public String getFamilyIncome() {
        return familyIncome;
    }
    
    public void setFamilyIncome(String familyIncome) {
        this.familyIncome = familyIncome;
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    
    public String getCurrentSemester() {
        return currentSemester;
    }
    
    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }
    
    public String getCgpa() {
        return cgpa;
    }
    
    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
    
    public String getAttendancePercentage() {
        return attendancePercentage;
    }
    
    public void setAttendancePercentage(String attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
