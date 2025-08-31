package com.nsp.portal.dto;

import jakarta.validation.constraints.*;

/**
 * DTO for institute registration requests.
 * Contains all necessary information to create an institute user and profile.
 */
public class InstituteRegistrationRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    
    @NotBlank(message = "Institute name is required")
    private String instituteName;
    
    @NotBlank(message = "Institute code is required")
    private String instituteCode;
    
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
    public InstituteRegistrationRequest() {}
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
