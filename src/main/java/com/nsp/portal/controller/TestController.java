package com.nsp.portal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test Controller for verifying Swagger and basic functionality.
 * This controller is accessible without authentication for testing purposes.
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
@Tag(name = "Test", description = "Test endpoints for verification")
public class TestController {

    @Operation(summary = "Health Check", description = "Simple health check endpoint")
    @GetMapping("/health")
    public String health() {
        return "National Scholarship Portal API is running!";
    }

    @Operation(summary = "Public Info", description = "Public information endpoint")
    @GetMapping("/info")
    public String info() {
        return "Welcome to National Scholarship Portal API v1.0.0";
    }
}
