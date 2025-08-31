# National Scholarship Portal: Backend Documentation

This document outlines the backend architecture and development plan for the National Scholarship Portal. The backend is a RESTful API built with Spring Boot to serve a React frontend.

## 1. Overview

The core architecture follows a standard three-layer pattern:

- **Controller Layer**: Handles incoming HTTP requests, validates input, and routes them to the service layer.
- **Service Layer**: Contains the core business logic of the application.
- **Repository Layer**: Manages data persistence and communication with the database using Spring Data JPA.

**Security**: Authentication and authorization are handled using Spring Security with JSON Web Tokens (JWT). Each user has a specific role (STUDENT, INSTITUTE, STATE_OFFICER, MINISTRY), and API endpoints are protected based on these roles.

## 2. Database Schema (JPA Entities)

The following entities represent the core data models of our application.

### User
The base entity for all actors in the system.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password; // Hashed using BCrypt
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
}

public enum Role {
    STUDENT, INSTITUTE, STATE_OFFICER, MINISTRY
}
```

### StudentProfile
Contains student-specific details. Linked one-to-one with a User.

```java
@Entity
public class StudentProfile {
    @Id
    private Long id; // Foreign key from User

    @OneToOne @MapsId
    private User user;

    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String domicileState;
    private String aadharNumber;
    // ... other personal and academic details
}
```

### InstituteProfile
Contains institute-specific details.

```java
@Entity
public class InstituteProfile {
    @Id
    private Long id; // Foreign key from User

    @OneToOne @MapsId
    private User user;

    private String instituteName;
    private String instituteCode;
    private boolean isRegistrationApproved = false;
    // ... other details like address, DISE code, etc.
}
```

### ScholarshipScheme
Stores details about available scholarships.

```java
@Entity
public class ScholarshipScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schemeName;
    private String description;
    private String eligibilityCriteria;
}
```

### ScholarshipApplication
The central entity linking a student to a scheme and tracking its progress.

```java
@Entity
public class ScholarshipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile student;

    @ManyToOne
    private ScholarshipScheme scheme;

    private LocalDate applicationDate;
    private Double familyAnnualIncome;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    // ... all other fields from the application form
}

public enum ApplicationStatus {
    APPLIED,
    PENDING_INSTITUTE_VERIFICATION,
    REJECTED_BY_INSTITUTE,
    PENDING_STATE_VERIFICATION,
    REJECTED_BY_STATE,
    PENDING_MINISTRY_APPROVAL,
    REJECTED_BY_MINISTRY,
    GRANTED
}
```

### ApplicationDocument
Manages uploaded documents for each application.

```java
@Entity
public class ApplicationDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ScholarshipApplication application;

    private String documentType; // e.g., "AADHAR_CARD"
    private String filePath;     // Path to the stored file
}
```

## 3. API Endpoints & Controller Breakdown

### AuthController (Public)
- `POST /api/auth/register/student`: Registers a new student.
- `POST /api/auth/register/institute`: Submits an institute registration request.
- `POST /api/auth/login`: Authenticates any user and returns a JWT.

### StudentController (Role: STUDENT)
- `GET /api/schemes`: Fetches all available scholarship schemes.
- `POST /api/applications`: Submits a new scholarship application (multipart request for documents).
- `GET /api/applications`: Gets all applications submitted by the logged-in student.
- `GET /api/student/profile`: Gets the logged-in student's profile.
- `PUT /api/student/profile`: Updates the student's profile.

### InstituteController (Role: INSTITUTE)
- `GET /api/institute/applications/pending`: Fetches pending student applications for the institute.
- `POST /api/institute/applications/{appId}/verify`: Verifies a student application and forwards it.
- `POST /api/institute/applications/{appId}/reject`: Rejects a student's application.

### StateController (Role: STATE_OFFICER)
- `GET /api/state/applications/pending`: Fetches pending applications forwarded by institutes.
- `POST /api/state/applications/{appId}/approve`: Approves an application and forwards it to the Ministry.
- `POST /api/state/applications/{appId}/reject`: Rejects an application.
- `GET /api/state/institutes/pending`: Fetches pending institute registration requests.
- `POST /api/state/institutes/{regId}/approve`: Approves an institute registration request.

### MinistryController (Role: MINISTRY)
- `GET /api/ministry/applications/pending`: Fetches all applications approved by State Officers.
- `POST /api/ministry/applications/{appId}/grant`: Grants the scholarship.
- `POST /api/ministry/applications/{appId}/reject`: Rejects the final application.
- `GET /api/ministry/institutes/pending`: Fetches pending institute requests.
- `POST /api/ministry/institutes/{regId}/approve`: Approves an institute's registration, activating their account.

## 4. Task Allocation for the Team

### Developer 1: Core & Authentication
**Focus**: Project setup, security, and user management.

**Tasks**:
- Initialize the Spring Boot project with required dependencies.
- Implement JWT-based security configuration (JwtUtil, SecurityConfig, UserDetailsService).
- Create the User entity and UserRepository.
- Build the AuthController and AuthService for registration and login logic.

### Developer 2: Student Module
**Focus**: All functionalities for the student user.

**Tasks**:
- Create StudentProfile, ScholarshipScheme, ScholarshipApplication, and ApplicationDocument entities and repositories.
- Build the StudentController and StudentService.
- Implement logic for viewing schemes, submitting applications, and checking application status.

### Developer 3: Institute Module
**Focus**: The verification workflow from the institute's perspective.

**Tasks**:
- Create the InstituteProfile entity and repository.
- Build the InstituteController and InstituteService.
- Implement logic for fetching, verifying, and rejecting student applications.

### Developer 4: State Nodal Officer Module
**Focus**: The verification workflow at the state level.

**Tasks**:
- Build the StateController and StateService.
- Implement logic to fetch, approve, and reject student applications.
- Implement logic to process new institute registration requests.

### Developer 5: Ministry Module & File Handling
**Focus**: Final approval stage and a common service for file management.

**Tasks**:
- Build the MinistryController and MinistryService.
- Implement final grant/reject logic for student applications.
- Implement final approval logic for institute registrations.
- Create a generic FileStorageService to handle all file uploads and retrievals.

## 5. Setup & General Instructions

**Version Control**: Use Git. Create a main branch and feature branches for each module (e.g., `feature/student-module`).

**Database**: Use a shared PostgreSQL or MySQL database for development.

**Configuration**: Store database credentials and JWT secrets in `application.properties`.

**API Testing**: Use Postman to test endpoints. Maintain a shared Postman collection.

## 6. Maven Dependencies (pom.xml)

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Database Driver (Example: PostgreSQL) -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- JWT Libraries -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```
