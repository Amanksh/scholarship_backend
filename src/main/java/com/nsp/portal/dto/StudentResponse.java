package com.nsp.portal.dto;

import com.nsp.portal.entity.StudentProfile;
import com.nsp.portal.entity.User;

/**
 * Response DTO for student operations.
 * Contains student profile and user information.
 */
public class StudentResponse {
    
    private String message;
    private StudentProfile profile;
    private User user;
    private boolean success;
    
    // Default constructor
    public StudentResponse() {}
    
    // Constructor for success response
    public StudentResponse(String message, StudentProfile profile, User user) {
        this.message = message;
        this.profile = profile;
        this.user = user;
        this.success = true;
    }
    
    // Constructor for error response
    public StudentResponse(String message) {
        this.message = message;
        this.success = false;
    }
    
    // Constructor for success without data
    public StudentResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public StudentProfile getProfile() {
        return profile;
    }
    
    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
