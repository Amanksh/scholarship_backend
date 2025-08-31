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
     * @param username the email address of the user
     * @return UserDetails object for Spring Security
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user by email using userRepository.findByEmail(username)
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        
        // Create UserDetails object with proper authorities
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
            .build();
    }
}
