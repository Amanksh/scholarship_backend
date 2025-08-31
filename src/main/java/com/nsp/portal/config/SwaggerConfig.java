package com.nsp.portal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI configuration for API documentation.
 * Provides comprehensive API documentation with security scheme information.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("National Scholarship Portal API")
                        .description("RESTful API for the National Scholarship Portal. " +
                                "This API provides endpoints for student applications, " +
                                "institute verification, state approval, and ministry final approval.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("NSP Development Team")
                                .email("dev@nsp.gov.in")
                                .url("https://nsp.gov.in"))
                        .license(new License()
                                .name("Government License")
                                .url("https://nsp.gov.in/license")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication", 
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token for authentication")));
    }
}
