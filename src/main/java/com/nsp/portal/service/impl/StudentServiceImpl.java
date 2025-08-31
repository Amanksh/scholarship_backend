package com.nsp.portal.service.impl;

import com.nsp.portal.dto.StudentProfileUpdateRequest;
import com.nsp.portal.entity.ScholarshipScheme;
import com.nsp.portal.entity.ScholarshipApplication;
import com.nsp.portal.entity.StudentProfile;
import com.nsp.portal.entity.ApplicationDocument;
import com.nsp.portal.entity.User;
import com.nsp.portal.enums.ApplicationStatus;
import com.nsp.portal.repository.ScholarshipSchemeRepository;
import com.nsp.portal.repository.ScholarshipApplicationRepository;
import com.nsp.portal.repository.StudentProfileRepository;
import com.nsp.portal.repository.ApplicationDocumentRepository;
import com.nsp.portal.repository.UserRepository;
import com.nsp.portal.service.StudentService;
import com.nsp.portal.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of StudentService for student operations.
 * 
 * This service implementation is responsible for:
 * - Retrieving and filtering scholarship schemes
 * - Processing scholarship application submissions
 * - Managing document uploads for applications
 * - Handling student profile operations
 * - Business logic validation for applications
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private ScholarshipSchemeRepository scholarshipSchemeRepository;
    
    @Autowired
    private ScholarshipApplicationRepository applicationRepository;
    
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    @Autowired
    private ApplicationDocumentRepository documentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    /**
     * Retrieves all available scholarship schemes.
     * 
     * @return list of available scholarship schemes
     */
    @Override
    public Object getAllSchemes() {
        try {
            List<ScholarshipScheme> schemes = scholarshipSchemeRepository.findByIsActiveTrue();
            
            // Filter schemes based on current date and application period
            LocalDate currentDate = LocalDate.now();
            List<ScholarshipScheme> availableSchemes = schemes.stream()
                .filter(scheme -> scheme.getApplicationStartDate().isBefore(currentDate) || 
                                scheme.getApplicationStartDate().isEqual(currentDate))
                .filter(scheme -> scheme.getApplicationEndDate().isAfter(currentDate) || 
                                scheme.getApplicationEndDate().isEqual(currentDate))
                .toList();
            
            return availableSchemes;
        } catch (Exception e) {
            return "Error retrieving schemes: " + e.getMessage();
        }
    }
    
    /**
     * Submits a new scholarship application with documents.
     * 
     * @param applicationData the application form data (JSON string)
     * @param documents the uploaded documents
     * @return application submission result
     */
    @Override
    @Transactional
    public Object submitApplication(String applicationData, MultipartFile[] documents) {
        try {
            // TODO: Parse applicationData JSON string to extract schemeId and other details
            // For now, we'll create a basic application
            
            // Create a basic application (this would be enhanced with proper JSON parsing)
            ScholarshipApplication application = new ScholarshipApplication();
            // Set application details based on parsed data
            
            // Save the application
            ScholarshipApplication savedApplication = applicationRepository.save(application);
            
            // Handle document uploads if provided
            if (documents != null && documents.length > 0) {
                for (MultipartFile document : documents) {
                    String fileName = fileStorageService.storeFile(document, "scholarship_documents");
                    
                    ApplicationDocument doc = new ApplicationDocument();
                    doc.setFilePath(fileName);
                    doc.setOriginalFileName(document.getOriginalFilename());
                    doc.setFileExtension(document.getOriginalFilename().substring(document.getOriginalFilename().lastIndexOf(".") + 1));
                    doc.setFileSize(document.getSize());
                    doc.setApplication(savedApplication);
                    doc.setDocumentType("SUPPORTING_DOCUMENT");
                    
                    documentRepository.save(doc);
                }
            }
            
            return "Application submitted successfully with ID: " + savedApplication.getId();
            
        } catch (Exception e) {
            return "Error submitting application: " + e.getMessage();
        }
    }
    
    /**
     * Gets all applications submitted by a specific student.
     * 
     * @param studentId the student's user ID
     * @return list of student's applications
     */
    @Override
    public Object getStudentApplications(Long studentId) {
        try {
            List<ScholarshipApplication> applications = applicationRepository.findByStudentId(studentId);
            return applications;
        } catch (Exception e) {
            return "Error retrieving applications: " + e.getMessage();
        }
    }
    
    /**
     * Gets the profile information for a specific student.
     * 
     * @param studentId the student's user ID
     * @return student profile information
     */
    @Override
    public Object getStudentProfile(Long studentId) {
        try {
            StudentProfile profile = studentProfileRepository.findByUserId(studentId).orElse(null);
            if (profile == null) {
                return "Student profile not found";
            }
            return profile;
        } catch (Exception e) {
            return "Error retrieving profile: " + e.getMessage();
        }
    }
    
    /**
     * Updates the profile information for a specific student.
     * 
     * @param studentId the student's user ID
     * @param profileUpdate the profile update data
     * @return updated profile information
     */
    @Override
    @Transactional
    public Object updateStudentProfile(Long studentId, Object profileUpdate) {
        try {
            StudentProfile existingProfile = studentProfileRepository.findByUserId(studentId).orElse(null);
            if (existingProfile == null) {
                return "Student profile not found";
            }
            
            // Cast the profileUpdate to StudentProfileUpdateRequest
            if (profileUpdate instanceof StudentProfileUpdateRequest) {
                StudentProfileUpdateRequest updateRequest = (StudentProfileUpdateRequest) profileUpdate;
                
                // Update allowed fields
                if (updateRequest.getName() != null) {
                    existingProfile.setName(updateRequest.getName());
                }
                if (updateRequest.getDateOfBirth() != null) {
                    existingProfile.setDateOfBirth(updateRequest.getDateOfBirth());
                }
                if (updateRequest.getGender() != null) {
                    existingProfile.setGender(updateRequest.getGender());
                }
                if (updateRequest.getDomicileState() != null) {
                    existingProfile.setDomicileState(updateRequest.getDomicileState());
                }
                if (updateRequest.getFatherName() != null) {
                    existingProfile.setFatherName(updateRequest.getFatherName());
                }
                if (updateRequest.getMotherName() != null) {
                    existingProfile.setMotherName(updateRequest.getMotherName());
                }
                if (updateRequest.getCategory() != null) {
                    existingProfile.setCategory(updateRequest.getCategory());
                }
                if (updateRequest.getReligion() != null) {
                    existingProfile.setReligion(updateRequest.getReligion());
                }
                if (updateRequest.getAddress() != null) {
                    existingProfile.setAddress(updateRequest.getAddress());
                }
                if (updateRequest.getDistrict() != null) {
                    existingProfile.setDistrict(updateRequest.getDistrict());
                }
                if (updateRequest.getPincode() != null) {
                    existingProfile.setPincode(updateRequest.getPincode());
                }
                if (updateRequest.getBankAccountNumber() != null) {
                    existingProfile.setBankAccountNumber(updateRequest.getBankAccountNumber());
                }
                if (updateRequest.getBankName() != null) {
                    existingProfile.setBankName(updateRequest.getBankName());
                }
                if (updateRequest.getIfscCode() != null) {
                    existingProfile.setIfscCode(updateRequest.getIfscCode());
                }
                
                // Save the updated profile
                StudentProfile updatedProfile = studentProfileRepository.save(existingProfile);
                return updatedProfile;
            } else {
                return "Invalid profile update data format";
            }
            
        } catch (Exception e) {
            return "Error updating profile: " + e.getMessage();
        }
    }
}
