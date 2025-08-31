package com.nsp.portal.repository;

import com.nsp.portal.entity.ScholarshipApplication;
import com.nsp.portal.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for ScholarshipApplication entity.
 * Provides data access methods for scholarship application operations.
 */
@Repository
public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
    
    /**
     * Find applications by student ID
     */
    List<ScholarshipApplication> findByStudentId(Long studentId);
    
    /**
     * Find applications by status
     */
    List<ScholarshipApplication> findByStatus(ApplicationStatus status);
    
    /**
     * Find applications by scheme ID
     */
    List<ScholarshipApplication> findBySchemeId(Long schemeId);
    
    /**
     * Find applications by student ID and status
     */
    List<ScholarshipApplication> findByStudentIdAndStatus(Long studentId, ApplicationStatus status);
    
    /**
     * Find applications by application date range
     */
    List<ScholarshipApplication> findByApplicationDateBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Find applications by family annual income less than or equal to given amount
     */
    List<ScholarshipApplication> findByFamilyAnnualIncomeLessThanEqual(java.math.BigDecimal maxIncome);
    
    /**
     * Find applications by student's domicile state
     */
    List<ScholarshipApplication> findByStudentDomicileState(String domicileState);
    
    /**
     * Find applications by student's category
     */
    List<ScholarshipApplication> findByStudentCategory(String category);
    
    /**
     * Find applications pending institute verification
     */
    List<ScholarshipApplication> findPendingInstituteVerification();
    
    /**
     * Find applications pending state verification
     */
    List<ScholarshipApplication> findPendingStateVerification();
    
    /**
     * Find applications pending ministry approval
     */
    List<ScholarshipApplication> findPendingMinistryApproval();
    
    /**
     * Find applications by academic year
     */
    List<ScholarshipApplication> findByAcademicYear(String academicYear);
}
