package com.nsp.portal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

/**
 * DTO for updating institute profile information.
 * 
 * This DTO contains fields that institutes can update in their profile.
 * All fields are optional to allow partial updates.
 */
public class InstituteProfileUpdateRequest {
    
    private String instituteName;
    private String diseCode;
    private String address;
    private String district;
    private String state;
    private String pincode;
    
    @Email(message = "Contact person email must be a valid email address")
    private String contactPersonEmail;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact person mobile must be exactly 10 digits")
    private String contactPersonMobile;
    
    private String contactPersonName;
    private String instituteType;
    private String affiliationBody;
    private String establishmentYear;
    
    // Default constructor
    public InstituteProfileUpdateRequest() {}
    
    // Constructor with all fields
    public InstituteProfileUpdateRequest(String instituteName, String diseCode, String address, 
                                       String district, String state, String pincode, 
                                       String contactPersonEmail, String contactPersonMobile, 
                                       String contactPersonName, String instituteType, 
                                       String affiliationBody, String establishmentYear) {
        this.instituteName = instituteName;
        this.diseCode = diseCode;
        this.address = address;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.contactPersonEmail = contactPersonEmail;
        this.contactPersonMobile = contactPersonMobile;
        this.contactPersonName = contactPersonName;
        this.instituteType = instituteType;
        this.affiliationBody = affiliationBody;
        this.establishmentYear = establishmentYear;
    }
    
    // Getters and Setters
    public String getInstituteName() {
        return instituteName;
    }
    
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
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
    
    public String getContactPersonEmail() {
        return contactPersonEmail;
    }
    
    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }
    
    public String getContactPersonMobile() {
        return contactPersonMobile;
    }
    
    public void setContactPersonMobile(String contactPersonMobile) {
        this.contactPersonMobile = contactPersonMobile;
    }
    
    public String getContactPersonName() {
        return contactPersonName;
    }
    
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
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
