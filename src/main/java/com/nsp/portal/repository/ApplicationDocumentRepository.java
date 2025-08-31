package com.nsp.portal.repository;

import com.nsp.portal.entity.ApplicationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for ApplicationDocument entity.
 * Provides data access methods for application document operations.
 */
@Repository
public interface ApplicationDocumentRepository extends JpaRepository<ApplicationDocument, Long> {
    
    /**
     * Find documents by application ID
     */
    List<ApplicationDocument> findByApplicationId(Long applicationId);
    
    /**
     * Find documents by document type
     */
    List<ApplicationDocument> findByDocumentType(String documentType);
    
    /**
     * Find documents by application ID and document type
     */
    List<ApplicationDocument> findByApplicationIdAndDocumentType(Long applicationId, String documentType);
    
    /**
     * Find verified documents
     */
    List<ApplicationDocument> findByIsVerifiedTrue();
    
    /**
     * Find unverified documents
     */
    List<ApplicationDocument> findByIsVerifiedFalse();
    
    /**
     * Find documents by application ID and verification status
     */
    List<ApplicationDocument> findByApplicationIdAndIsVerified(Long applicationId, boolean isVerified);
    
    /**
     * Find documents by file extension
     */
    List<ApplicationDocument> findByFileExtension(String fileExtension);
}
