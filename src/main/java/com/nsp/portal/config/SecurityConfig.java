package com.nsp.portal.config;

import com.nsp.portal.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jakarta.servlet.Filter;

import java.util.Arrays;

/**
 * Spring Security Configuration class.
 * 
 * DEVELOPER 1 TASK: Implement the SecurityFilterChain bean to:
 * 1. Configure CORS for cross-origin requests
 * 2. Disable CSRF protection (not needed for JWT-based API)
 * 3. Set session management policy to stateless
 * 4. Define authorization rules for all API endpoints based on roles
 * 5. Configure JWT filter
 * 6. Define PasswordEncoder bean
 * 
 * This configuration is responsible for:
 * - Setting up security rules for the application
 * - Configuring authentication and authorization
 * - Managing CORS and CSRF settings
 * - Setting up JWT-based security
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    /**
     * Configures the SecurityFilterChain for the application.
     * 
     * TODO: DEVELOPER 1 - Implement this method:
     * 1. Configure CORS using corsConfigurationSource()
     * 2. Disable CSRF protection
     * 3. Set session creation policy to STATELESS
     * 4. Configure authorization rules for all endpoints:
     *    - Public endpoints: /api/auth/**
     *    - STUDENT role: /api/schemes, /api/applications, /api/student/**
     *    - INSTITUTE role: /api/institute/**
     *    - STATE_OFFICER role: /api/state/**
     *    - MINISTRY role: /api/ministry/**
     * 5. Add JWT filter before UsernamePasswordAuthenticationFilter
     * 6. Return the configured SecurityFilterChain
     * 
     * @param http the HttpSecurity object to configure
     * @return configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: Implement security configuration
        // 1. Configure CORS
        // 2. Disable CSRF
        // 3. Set session policy
        // 4. Configure authorization rules
        // 5. Add JWT filter
        // 6. Return configured chain
        
        http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/schemes").hasRole("STUDENT")
                .requestMatchers("/api/applications/**").hasRole("STUDENT")
                .requestMatchers("/api/student/**").hasRole("STUDENT")
                .requestMatchers("/api/institute/**").hasRole("INSTITUTE")
                .requestMatchers("/api/state/**").hasRole("STATE_OFFICER")
                .requestMatchers("/api/ministry/**").hasRole("MINISTRY")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    /**
     * Configures CORS for cross-origin requests.
     * 
     * @return CORS configuration source
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    /**
     * Defines the PasswordEncoder bean for password hashing.
     * 
     * TODO: DEVELOPER 1 - This method is already implemented.
     * BCryptPasswordEncoder is used for hashing passwords securely.
     * 
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Defines the AuthenticationManager bean.
     * 
     * @param authConfig the authentication configuration
     * @return AuthenticationManager instance
     * @throws Exception if configuration fails
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
