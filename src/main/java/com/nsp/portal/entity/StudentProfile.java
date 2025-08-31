package com.nsp.portal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

/**
 * StudentProfile entity containing student-specific details.
 * Linked one-to-one with a User entity.
 */
@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    
    @Id
    private Long id; // Foreign key from User
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String gender;
    
    @NotBlank(message = "Domicile state is required")
    @Column(nullable = false)
    private String domicileState;
    
    @NotBlank(message = "Aadhar number is required")
    @Column(unique = true, nullable = false, length = 12)
    private String aadharNumber;
    
    // Additional fields for comprehensive student information
    private String fatherName;
    private String motherName;
    private String category; // SC, ST, OBC, General
    private String religion;
    private String address;
    private String district;
    private String pincode;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    
    // Default constructor
    public StudentProfile() {}
    
    // Constructor with required fields
    public StudentProfile(User user, String name, LocalDate dateOfBirth, String gender, 
                        String domicileState, String aadharNumber) {
        this.user = user;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.domicileState = domicileState;
        this.aadharNumber = aadharNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    
    public String getAadharNumber() {
        return aadharNumber;
    }
    
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
    
    // Additional getters and setters for extended fields
    public String getFatherName() {
        return fatherName;
    }
    
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    
    public String getMotherName() {
        return motherName;
    }
    
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getReligion() {
        return religion;
    }
    
    public void setReligion(String religion) {
        this.religion = religion;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getPincode() {
        return pincode;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
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
}
