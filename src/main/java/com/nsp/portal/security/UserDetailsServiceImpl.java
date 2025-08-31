package com.nsp.portal.security;

import com.nsp.portal.entity.User;
import com.nsp.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Custom UserDetailsService implementation for Spring Security.
 * 
 * DEVELOPER 1 TASK: Implement the loadUserByUsername method to fetch a user from UserRepository
 * and convert it to Spring Security's UserDetails object.
 * 
 * This service is responsible for:
 * 1. Loading user details by username (email in our case)
 * 2. Converting our User entity to Spring Security's UserDetails
 * 3. Setting up authorities based on user roles
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Loads user details by username (email in our system).
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Use userRepository.findByEmail(username) to find the user
     * 2. If user not found, throw UsernameNotFoundException
     * 3. Convert User entity to UserDetails with proper authorities
     * 4. Set authorities based on user.getRole()
     * 5. Return UserDetails object
     * 
     * @param username the email address of the user
     * @return UserDetails object for Spring Security
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Implement user loading logic
        // 1. Find user by email using userRepository.findByEmail(username)
        // 2. Handle case when user not found
        // 3. Create UserDetails object with proper authorities
        // 4. Return the UserDetails object
        
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
