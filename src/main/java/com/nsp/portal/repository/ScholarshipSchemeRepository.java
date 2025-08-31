package com.nsp.portal.repository;

import com.nsp.portal.entity.ScholarshipScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for ScholarshipScheme entity.
 * Provides data access methods for scholarship scheme operations.
 */
@Repository
public interface ScholarshipSchemeRepository extends JpaRepository<ScholarshipScheme, Long> {
    
    /**
     * Find all active scholarship schemes
     */
    List<ScholarshipScheme> findByIsActiveTrue();
    
    /**
     * Find scholarship schemes by category
     */
    List<ScholarshipScheme> findByCategory(String category);
    
    /**
     * Find scholarship schemes by domicile state
     */
    List<ScholarshipScheme> findByDomicileState(String domicileState);
    
    /**
     * Find scholarship schemes by academic level
     */
    List<ScholarshipScheme> findByAcademicLevel(String academicLevel);
    
    /**
     * Find scholarship schemes by institute type
     */
    List<ScholarshipScheme> findByInstituteType(String instituteType);
    
    /**
     * Find scholarship schemes where application period is currently open
     */
    List<ScholarshipScheme> findByIsActiveTrueAndApplicationStartDateBeforeAndApplicationEndDateAfter(
            LocalDate startDate, LocalDate endDate);
    
    /**
     * Find scholarship schemes by subject
     */
    List<ScholarshipScheme> findBySubject(String subject);
    
    /**
     * Find scholarship schemes by gender eligibility
     */
    List<ScholarshipScheme> findByGender(String gender);
}
