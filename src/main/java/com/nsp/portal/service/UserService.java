package com.nsp.portal.service;

/**
 * Service interface for user operations.
 * Provides methods for user management and authentication.
 */
public interface UserService {
    
    /**
     * Gets the user ID by email address.
     * 
     * @param email the user's email address
     * @return the user ID, or null if not found
     */
    Long getUserIdByEmail(String email);
    
    /**
     * Gets the user by email address.
     * 
     * @param email the user's email address
     * @return the user entity, or null if not found
     */
    Object getUserByEmail(String email);
}
