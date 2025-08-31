package com.nsp.portal.service.impl;

import com.nsp.portal.dto.InstituteProfileUpdateRequest;
import com.nsp.portal.dto.ApplicationVerificationRequest;
import com.nsp.portal.entity.InstituteProfile;
import com.nsp.portal.entity.ScholarshipApplication;
import com.nsp.portal.entity.StudentProfile;
import com.nsp.portal.entity.ScholarshipScheme;
import com.nsp.portal.enums.ApplicationStatus;
import com.nsp.portal.repository.InstituteProfileRepository;
import com.nsp.portal.repository.ScholarshipApplicationRepository;
import com.nsp.portal.repository.StudentProfileRepository;
import com.nsp.portal.repository.ScholarshipSchemeRepository;
import com.nsp.portal.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of InstituteService for institute operations.
 * 
 * This service implementation is responsible for:
 * - Managing institute profiles
 * - Handling student application verification
 * - Providing institute dashboard data
 * - Managing application workflow
 */
@Service
public class InstituteServiceImpl implements InstituteService {
    
    @Autowired
    private InstituteProfileRepository instituteProfileRepository;
    
    @Autowired
    private ScholarshipApplicationRepository applicationRepository;
    
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    @Autowired
    private ScholarshipSchemeRepository schemeRepository;
    
    /**
     * Gets the institute's profile information.
     * 
     * @param instituteId the institute's user ID
     * @return institute profile information
     */
    @Override
    public Object getInstituteProfile(Long instituteId) {
        try {
            InstituteProfile profile = instituteProfileRepository.findByUserId(instituteId).orElse(null);
            if (profile == null) {
                return "Institute profile not found";
            }
            return profile;
        } catch (Exception e) {
            return "Error retrieving profile: " + e.getMessage();
        }
    }
    
    /**
     * Updates the institute's profile information.
     * 
     * @param instituteId the institute's user ID
     * @param profileUpdate the profile update request
     * @return updated profile information
     */
    @Override
    @Transactional
    public Object updateInstituteProfile(Long instituteId, InstituteProfileUpdateRequest profileUpdate) {
        try {
            InstituteProfile existingProfile = instituteProfileRepository.findByUserId(instituteId).orElse(null);
            if (existingProfile == null) {
                return "Institute profile not found";
            }
            
            // Update allowed fields
            if (profileUpdate.getInstituteName() != null) {
                existingProfile.setInstituteName(profileUpdate.getInstituteName());
            }
            if (profileUpdate.getDiseCode() != null) {
                existingProfile.setDiseCode(profileUpdate.getDiseCode());
            }
            if (profileUpdate.getAddress() != null) {
                existingProfile.setAddress(profileUpdate.getAddress());
            }
            if (profileUpdate.getDistrict() != null) {
                existingProfile.setDistrict(profileUpdate.getDistrict());
            }
            if (profileUpdate.getState() != null) {
                existingProfile.setState(profileUpdate.getState());
            }
            if (profileUpdate.getPincode() != null) {
                existingProfile.setPincode(profileUpdate.getPincode());
            }
            if (profileUpdate.getContactPersonName() != null) {
                existingProfile.setContactPersonName(profileUpdate.getContactPersonName());
            }
            if (profileUpdate.getContactPersonMobile() != null) {
                existingProfile.setContactPersonMobile(profileUpdate.getContactPersonMobile());
            }
            if (profileUpdate.getContactPersonEmail() != null) {
                existingProfile.setContactPersonEmail(profileUpdate.getContactPersonEmail());
            }
            if (profileUpdate.getInstituteType() != null) {
                existingProfile.setInstituteType(profileUpdate.getInstituteType());
            }
            if (profileUpdate.getAffiliationBody() != null) {
                existingProfile.setAffiliationBody(profileUpdate.getAffiliationBody());
            }
            if (profileUpdate.getEstablishmentYear() != null) {
                existingProfile.setEstablishmentYear(profileUpdate.getEstablishmentYear());
            }
            
            // Save the updated profile
            InstituteProfile updatedProfile = instituteProfileRepository.save(existingProfile);
            return updatedProfile;
            
        } catch (Exception e) {
            return "Error updating profile: " + e.getMessage();
        }
    }
    
    /**
     * Gets all pending applications for the institute to verify.
     * 
     * @param instituteId the institute's user ID
     * @return list of pending applications
     */
    @Override
    public Object getPendingApplications(Long instituteId) {
        try {
            // Get applications that are pending and belong to students from this institute
            List<ScholarshipApplication> pendingApplications = applicationRepository.findByStatus(ApplicationStatus.PENDING_INSTITUTE_VERIFICATION);
            // TODO: Filter by institute - need to add institute relationship to applications
            return pendingApplications;
        } catch (Exception e) {
            return "Error retrieving pending applications: " + e.getMessage();
        }
    }
    
    /**
     * Gets all applications for the institute with optional status filtering.
     * 
     * @param instituteId the institute's user ID
     * @param status optional status filter
     * @return list of applications
     */
    @Override
    public Object getAllApplications(Long instituteId, String status) {
        try {
            List<ScholarshipApplication> applications;
            if (status != null && !status.isEmpty()) {
                ApplicationStatus appStatus = ApplicationStatus.valueOf(status.toUpperCase());
                applications = applicationRepository.findByStatus(appStatus);
            } else {
                applications = applicationRepository.findAll();
            }
            // TODO: Filter by institute - need to add institute relationship to applications
            return applications;
        } catch (Exception e) {
            return "Error retrieving applications: " + e.getMessage();
        }
    }
    
    /**
     * Gets a specific application by ID.
     * 
     * @param instituteId the institute's user ID
     * @param applicationId the application ID
     * @return application details
     */
    @Override
    public Object getApplicationById(Long instituteId, Long applicationId) {
        try {
            ScholarshipApplication application = applicationRepository.findById(applicationId).orElse(null);
            // TODO: Validate institute access - need to add institute relationship to applications
            return application;
        } catch (Exception e) {
            return "Error retrieving application: " + e.getMessage();
        }
    }
    
    /**
     * Verifies a student's scholarship application.
     * 
     * @param instituteId the institute's user ID
     * @param applicationId the application ID
     * @param verificationRequest the verification details
     * @return verification result
     */
    @Override
    @Transactional
    public Object verifyApplication(Long instituteId, Long applicationId, ApplicationVerificationRequest verificationRequest) {
        try {
            ScholarshipApplication application = applicationRepository.findById(applicationId).orElse(null);
            if (application == null) {
                return "Application not found";
            }
            
            // TODO: Validate institute access - need to add institute relationship to applications
            
            // Update application status
            application.setStatus(ApplicationStatus.PENDING_STATE_VERIFICATION);
            // TODO: Add institute verification fields to ScholarshipApplication entity
            
            // Save the updated application
            ScholarshipApplication savedApplication = applicationRepository.save(application);
            return "Application verified successfully. Application ID: " + savedApplication.getId();
            
        } catch (Exception e) {
            return "Error verifying application: " + e.getMessage();
        }
    }
    
    /**
     * Rejects a student's scholarship application.
     * 
     * @param instituteId the institute's user ID
     * @param applicationId the application ID
     * @param rejectionRequest the rejection details
     * @return rejection result
     */
    @Override
    @Transactional
    public Object rejectApplication(Long instituteId, Long applicationId, ApplicationVerificationRequest rejectionRequest) {
        try {
            ScholarshipApplication application = applicationRepository.findById(applicationId).orElse(null);
            if (application == null) {
                return "Application not found";
            }
            
            // TODO: Validate institute access - need to add institute relationship to applications
            
            // Update application status
            application.setStatus(ApplicationStatus.REJECTED_BY_INSTITUTE);
            // TODO: Add institute rejection fields to ScholarshipApplication entity
            
            // Save the updated application
            ScholarshipApplication savedApplication = applicationRepository.save(application);
            return "Application rejected successfully. Application ID: " + savedApplication.getId();
            
        } catch (Exception e) {
            return "Error rejecting application: " + e.getMessage();
        }
    }
    
    /**
     * Gets institute statistics and dashboard data.
     * 
     * @param instituteId the institute's user ID
     * @return institute dashboard statistics
     */
    @Override
    public Object getDashboard(Long instituteId) {
        try {
            Map<String, Object> dashboard = new HashMap<>();
            
            // Get counts for different application statuses
            long pendingCount = applicationRepository.countByStatus(ApplicationStatus.PENDING_INSTITUTE_VERIFICATION);
            long verifiedCount = applicationRepository.countByStatus(ApplicationStatus.PENDING_STATE_VERIFICATION);
            long rejectedCount = applicationRepository.countByStatus(ApplicationStatus.REJECTED_BY_INSTITUTE);
            long totalCount = applicationRepository.count();
            
            // TODO: Filter by institute - need to add institute relationship to applications and students
            
            dashboard.put("totalApplications", totalCount);
            dashboard.put("pendingApplications", pendingCount);
            dashboard.put("verifiedApplications", verifiedCount);
            dashboard.put("rejectedApplications", rejectedCount);
            dashboard.put("totalStudents", 0L); // TODO: Add institute relationship
            dashboard.put("verificationRate", totalCount > 0 ? (double) verifiedCount / totalCount * 100 : 0);
            
            return dashboard;
            
        } catch (Exception e) {
            return "Error retrieving dashboard: " + e.getMessage();
        }
    }
    
    /**
     * Gets all students registered with this institute.
     * 
     * @param instituteId the institute's user ID
     * @return list of registered students
     */
    @Override
    public Object getRegisteredStudents(Long instituteId) {
        try {
            // TODO: Add institute relationship to StudentProfile entity
            List<StudentProfile> students = studentProfileRepository.findAll();
            return students;
        } catch (Exception e) {
            return "Error retrieving students: " + e.getMessage();
        }
    }
    
    /**
     * Gets a specific student's details by ID.
     * 
     * @param instituteId the institute's user ID
     * @param studentId the student ID
     * @return student details
     */
    @Override
    public Object getStudentDetails(Long instituteId, Long studentId) {
        try {
            // TODO: Add institute relationship to StudentProfile entity
            StudentProfile student = studentProfileRepository.findById(studentId).orElse(null);
            return student;
        } catch (Exception e) {
            return "Error retrieving student details: " + e.getMessage();
        }
    }
    
    /**
     * Gets all scholarship schemes available for this institute.
     * 
     * @param instituteId the institute's user ID
     * @return list of available scholarship schemes
     */
    @Override
    public Object getAvailableSchemes(Long instituteId) {
        try {
            List<ScholarshipScheme> schemes = schemeRepository.findByIsActiveTrue();
            return schemes;
        } catch (Exception e) {
            return "Error retrieving schemes: " + e.getMessage();
        }
    }
    
    /**
     * Gets institute registration status and approval information.
     * 
     * @param instituteId the institute's user ID
     * @return registration status details
     */
    @Override
    public Object getRegistrationStatus(Long instituteId) {
        try {
            InstituteProfile profile = instituteProfileRepository.findByUserId(instituteId).orElse(null);
            if (profile == null) {
                return "Institute profile not found";
            }
            
            Map<String, Object> status = new HashMap<>();
            status.put("isRegistrationApproved", profile.getIsRegistrationApproved());
            status.put("registrationDate", profile.getRegistrationDate());
            status.put("approvalDate", profile.getApprovalDate());
            status.put("approvalStatus", profile.getIsRegistrationApproved() ? "APPROVED" : "PENDING");
            
            return status;
            
        } catch (Exception e) {
            return "Error retrieving registration status: " + e.getMessage();
        }
    }
}
