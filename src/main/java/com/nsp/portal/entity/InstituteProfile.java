package com.nsp.portal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * InstituteProfile entity containing institute-specific details.
 * Linked one-to-one with a User entity.
 */
@Entity
@Table(name = "institute_profiles")
public class InstituteProfile {
    
    @Id
    private Long id; // Foreign key from User
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    
    @NotBlank(message = "Institute name is required")
    @Column(nullable = false)
    private String instituteName;
    
    @NotBlank(message = "Institute code is required")
    @Column(unique = true, nullable = false)
    private String instituteCode;
    
    @NotNull(message = "Registration approval status is required")
    @Column(nullable = false)
    private boolean isRegistrationApproved = false;
    
    // Additional fields for comprehensive institute information
    private String diseCode; // District Information System for Education code
    private String address;
    private String district;
    private String state;
    private String pincode;
    private String contactPersonName;
    private String contactPersonMobile;
    private String contactPersonEmail;
    private String instituteType; // Government, Private, Aided
    private String affiliationBody; // CBSE, ICSE, State Board, etc.
    private String establishmentYear;
    
    // Default constructor
    public InstituteProfile() {}
    
    // Constructor with required fields
    public InstituteProfile(User user, String instituteName, String instituteCode) {
        this.user = user;
        this.instituteName = instituteName;
        this.instituteCode = instituteCode;
        this.isRegistrationApproved = false;
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
    
    public String getInstituteName() {
        return instituteName;
    }
    
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
    
    public String getInstituteCode() {
        return instituteCode;
    }
    
    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }
    
    public boolean isRegistrationApproved() {
        return isRegistrationApproved;
    }
    
    public void setRegistrationApproved(boolean registrationApproved) {
        isRegistrationApproved = registrationApproved;
    }
    
    // Additional getters and setters for extended fields
    public String getDiseCode() {
        return diseCode;
    }
    
    public void setDiseCode(String diseCode) {
        this.diseCode = diseCode;
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
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPincode() {
        return pincode;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public String getContactPersonName() {
        return contactPersonName;
    }
    
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
    
    public String getContactPersonMobile() {
        return contactPersonMobile;
    }
    
    public void setContactPersonMobile(String contactPersonMobile) {
        this.contactPersonMobile = contactPersonMobile;
    }
    
    public String getContactPersonEmail() {
        return contactPersonEmail;
    }
    
    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }
    
    public String getInstituteType() {
        return instituteType;
    }
    
    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }
    
    public String getAffiliationBody() {
        return affiliationBody;
    }
    
    public void setAffiliationBody(String affiliationBody) {
        this.affiliationBody = affiliationBody;
    }
    
    public String getEstablishmentYear() {
        return establishmentYear;
    }
    
    public void setEstablishmentYear(String establishmentYear) {
        this.establishmentYear = establishmentYear;
    }
}
