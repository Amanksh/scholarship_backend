package com.nsp.portal.repository;

import com.nsp.portal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for StudentProfile entity.
 * Provides data access methods for student profile operations.
 */
@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    
    /**
     * Find student profile by user ID
     */
    Optional<StudentProfile> findByUserId(Long userId);
    
    /**
     * Find student profiles by domicile state
     */
    List<StudentProfile> findByDomicileState(String domicileState);
    
    /**
     * Find student profiles by category
     */
    List<StudentProfile> findByCategory(String category);
    
    /**
     * Find student profile by Aadhar number
     */
    Optional<StudentProfile> findByAadharNumber(String aadharNumber);
    
    /**
     * Check if a student profile exists with the given Aadhar number
     */
    boolean existsByAadharNumber(String aadharNumber);
    
    /**
     * Find student profiles by district
     */
    List<StudentProfile> findByDistrict(String district);
}
