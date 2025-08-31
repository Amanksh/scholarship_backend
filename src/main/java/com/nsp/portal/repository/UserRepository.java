package com.nsp.portal.repository;

import com.nsp.portal.entity.User;
import com.nsp.portal.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides data access methods for user management operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find a user by email for the login process
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if a user exists with the given email
     */
    boolean existsByEmail(String email);
    
    /**
     * Find users by role
     */
    List<User> findByRole(Role role);
    
    /**
     * Find users by mobile number
     */
    Optional<User> findByMobileNumber(String mobileNumber);
    
    /**
     * Check if a user exists with the given mobile number
     */
    boolean existsByMobileNumber(String mobileNumber);
}
