package com.nsp.portal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT Utility class for handling JSON Web Token operations.
 * 
 * DEVELOPER 1 TASK: Implement the following methods for JWT operations:
 * 1. generateToken() - Generate JWT token for a user
 * 2. validateToken() - Validate if a JWT token is valid
 * 3. extractUsername() - Extract username from JWT token
 * 4. extractExpiration() - Extract expiration date from JWT token
 * 
 * This utility class is responsible for:
 * - Creating JWT tokens with user information
 * - Validating JWT tokens
 * - Extracting information from JWT tokens
 * - Managing token expiration
 */
@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}") // Default: 24 hours
    private Long expiration;
    
    /**
     * Generates a JWT token for the given user.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Create claims map with user information
     * 2. Set subject as username
    3. Set issued date as current time
     * 4. Set expiration date
     * 5. Sign the token with the secret key
     * 6. Return the generated token string
     * 
     * @param userDetails the user details to create token for
     * @return JWT token string
     */
    public String generateToken(UserDetails userDetails) {
        // TODO: Implement token generation logic
        // 1. Create claims map
        // 2. Set subject, issued date, expiration
        // 3. Sign and return token
        
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    /**
     * Creates a JWT token with the given claims and subject.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Use Jwts.builder() to create token
     * 2. Set claims, subject, issued date, expiration
     * 3. Sign with secret key using HS256 algorithm
     * 4. Return compact token string
     * 
     * @param claims the claims to include in the token
     * @param subject the subject (username) for the token
     * @return JWT token string
     */
    private String createToken(Map<String, Object> claims, String subject) {
        // TODO: Implement token creation logic
        // 1. Use Jwts.builder()
        // 2. Set claims, subject, dates
        // 3. Sign and return
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    
    /**
     * Validates if a JWT token is valid for the given user.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Extract username from token
     * 2. Check if username matches userDetails username
     * 3. Check if token is not expired
     * 4. Return true if valid, false otherwise
     * 
     * @param token the JWT token to validate
     * @param userDetails the user details to validate against
     * @return true if token is valid, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        // TODO: Implement token validation logic
        // 1. Extract username from token
        // 2. Check username match
        // 3. Check expiration
        // 4. Return validation result
        
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    /**
     * Extracts username from JWT token.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Extract claims from token
     * 2. Return the subject (username) from claims
     * 
     * @param token the JWT token
     * @return username extracted from token
     */
    public String extractUsername(String token) {
        // TODO: Implement username extraction logic
        // 1. Extract claims
        // 2. Return subject
        
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * Extracts expiration date from JWT token.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Extract claims from token
     * 2. Return the expiration date from claims
     * 
     * @param token the JWT token
     * @return expiration date extracted from token
     */
    public Date extractExpiration(String token) {
        // TODO: Implement expiration extraction logic
        // 1. Extract claims
        // 2. Return expiration date
        
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * Extracts a specific claim from JWT token.
     * 
     * @param token the JWT token
     * @param claimsResolver function to resolve the specific claim
     * @return the extracted claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * Extracts all claims from JWT token.
     * 
     * @param token the JWT token
     * @return all claims from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    /**
     * Checks if JWT token is expired.
     * 
     * @param token the JWT token
     * @return true if token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
