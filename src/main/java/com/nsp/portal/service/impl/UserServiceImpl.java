package com.nsp.portal.service.impl;

import com.nsp.portal.entity.User;
import com.nsp.portal.repository.UserRepository;
import com.nsp.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserService for user operations.
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Gets the user ID by email address.
     * 
     * @param email the user's email address
     * @return the user ID, or null if not found
     */
    @Override
    public Long getUserIdByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            return user != null ? user.getId() : null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Gets the user by email address.
     * 
     * @param email the user's email address
     * @return the user entity, or null if not found
     */
    @Override
    public Object getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
