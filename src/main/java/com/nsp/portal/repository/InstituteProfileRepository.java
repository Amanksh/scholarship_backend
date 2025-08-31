package com.nsp.portal.repository;

import com.nsp.portal.entity.InstituteProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for InstituteProfile entity.
 * Provides data access methods for institute profile operations.
 */
@Repository
public interface InstituteProfileRepository extends JpaRepository<InstituteProfile, Long> {
    
    /**
     * Find institute profile by user ID
     */
    Optional<InstituteProfile> findByUserId(Long userId);
    
    /**
     * Find institute profiles by registration approval status
     */
    List<InstituteProfile> findByIsRegistrationApproved(boolean isApproved);
    
    /**
     * Find institute profile by institute code
     */
    Optional<InstituteProfile> findByInstituteCode(String instituteCode);
    
    /**
     * Check if an institute profile exists with the given institute code
     */
    boolean existsByInstituteCode(String instituteCode);
    
    /**
     * Find institute profiles by state
     */
    List<InstituteProfile> findByState(String state);
    
    /**
     * Find institute profiles by district
     */
    List<InstituteProfile> findByDistrict(String district);
    
    /**
     * Find institute profiles by institute type
     */
    List<InstituteProfile> findByInstituteType(String instituteType);
    
    /**
     * Find institute profiles by DISE code
     */
    Optional<InstituteProfile> findByDiseCode(String diseCode);
}
