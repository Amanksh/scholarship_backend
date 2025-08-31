package com.nsp.portal.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Request Filter for intercepting HTTP requests and validating JWT tokens.
 * 
 * DEVELOPER 1 TASK: Implement the doFilterInternal method to:
 * 1. Intercept incoming requests
 * 2. Extract JWT token from Authorization header
 * 3. Validate the JWT token
 * 4. Set authentication in Spring Security context
 * 5. Continue with the filter chain
 * 
 * This filter is responsible for:
 * - Extracting JWT tokens from request headers
 * - Validating JWT tokens using JwtUtil
 * - Setting up authentication context for valid tokens
 * - Allowing requests to proceed with proper authentication
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    /**
     * Filters incoming HTTP requests to validate JWT tokens and set authentication.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Extract JWT token from Authorization header (Bearer token)
     * 2. Check if token exists and starts with "Bearer "
     * 3. Extract the actual token (remove "Bearer " prefix)
     * 4. Validate the token using JwtUtil
     * 5. Load user details if token is valid
     * 6. Set authentication in SecurityContextHolder
     * 7. Continue with filter chain
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain to continue
     * @throws ServletException if servlet error occurs
     * @throws IOException if I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        // Skip JWT processing for public endpoints
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/auth/") || 
            requestURI.startsWith("/api/test/") || 
            requestURI.startsWith("/swagger-ui/") || 
            requestURI.startsWith("/v3/api-docs/")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwt = null;
        
        // Extract JWT token from Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // Token is invalid, continue without authentication
                logger.warn("Invalid JWT token: " + e.getMessage());
            }
        }
        
        // Validate token and set authentication context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
