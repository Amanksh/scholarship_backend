# National Scholarship Portal - Complete Project Structure

This document outlines the complete project structure generated for the National Scholarship Portal backend.

## Project Overview

The National Scholarship Portal is a Spring Boot application with a three-layer architecture:
- **Controller Layer**: Handles HTTP requests and API endpoints
- **Service Layer**: Contains business logic
- **Repository Layer**: Manages data persistence

## Directory Structure

```
src/
└── main/
    ├── java/
    │   └── com/
    │       └── nsp/
    │           └── portal/
    │               ├── PortalApplication.java
    │               ├── config/
    │               │   └── SecurityConfig.java
    │               ├── controller/
    │               │   ├── AuthController.java
    │               │   ├── StudentController.java
    │               │   ├── InstituteController.java
    │               │   ├── StateController.java
    │               │   └── MinistryController.java
    │               ├── dto/
    │               │   ├── StudentRegistrationRequest.java
    │               │   ├── InstituteRegistrationRequest.java
    │               │   └── LoginRequest.java
    │               ├── entity/
    │               │   ├── User.java
    │               │   ├── StudentProfile.java
    │               │   ├── InstituteProfile.java
    │               │   ├── ScholarshipScheme.java
    │               │   ├── ScholarshipApplication.java
    │               │   └── ApplicationDocument.java
    │               ├── enums/
    │               │   ├── Role.java
    │               │   └── ApplicationStatus.java
    │               ├── repository/
    │               │   ├── UserRepository.java
    │               │   ├── StudentProfileRepository.java
    │               │   ├── InstituteProfileRepository.java
    │               │   ├── ScholarshipSchemeRepository.java
    │               │   ├── ScholarshipApplicationRepository.java
    │               │   └── ApplicationDocumentRepository.java
    │               ├── security/
    │               │   ├── UserDetailsServiceImpl.java
    │               │   ├── JwtUtil.java
    │               │   └── JwtRequestFilter.java
    │               ├── service/
    │               │   ├── AuthService.java
    │               │   ├── StudentService.java
    │               │   ├── InstituteService.java
    │               │   ├── StateService.java
    │               │   ├── MinistryService.java
    │               │   ├── FileStorageService.java
    │               │   └── impl/
    │               │       ├── AuthServiceImpl.java
    │               │       ├── StudentServiceImpl.java
    │               │       ├── InstituteServiceImpl.java
    │               │       ├── StateServiceImpl.java
    │               │       ├── MinistryServiceImpl.java
    │               │       └── FileStorageServiceImpl.java
    │               └── util/
    └── resources/
        └── application.properties
pom.xml
README.md
PROJECT_STRUCTURE.md
```

## File Descriptions

### Core Application Files
- **PortalApplication.java**: Main Spring Boot application class
- **pom.xml**: Maven dependencies and project configuration
- **application.properties**: Application configuration and database settings

### Configuration
- **SecurityConfig.java**: Spring Security configuration with JWT and role-based access control

### Controllers (API Endpoints)
- **AuthController.java**: Authentication endpoints (register/login)
- **StudentController.java**: Student-specific operations
- **InstituteController.java**: Institute verification operations
- **StateController.java**: State-level approval operations
- **MinistryController.java**: Ministry-level final approval operations

### DTOs (Data Transfer Objects)
- **StudentRegistrationRequest.java**: Student registration data structure
- **InstituteRegistrationRequest.java**: Institute registration data structure
- **LoginRequest.java**: User login data structure

### Entities (JPA Models)
- **User.java**: Base user entity with authentication details
- **StudentProfile.java**: Student-specific profile information
- **InstituteProfile.java**: Institute-specific profile information
- **ScholarshipScheme.java**: Available scholarship schemes
- **ScholarshipApplication.java**: Scholarship application workflow
- **ApplicationDocument.java**: Document management for applications

### Enums
- **Role.java**: User roles (STUDENT, INSTITUTE, STATE_OFFICER, MINISTRY)
- **ApplicationStatus.java**: Application workflow statuses

### Repositories (Data Access)
- **UserRepository.java**: User data access operations
- **StudentProfileRepository.java**: Student profile data access
- **InstituteProfileRepository.java**: Institute profile data access
- **ScholarshipSchemeRepository.java**: Scholarship scheme data access
- **ScholarshipApplicationRepository.java**: Application data access
- **ApplicationDocumentRepository.java**: Document data access

### Security Components
- **UserDetailsServiceImpl.java**: Spring Security user details service
- **JwtUtil.java**: JWT token utility operations
- **JwtRequestFilter.java**: JWT authentication filter

### Services (Business Logic)
- **AuthService.java**: Authentication business logic interface
- **StudentService.java**: Student operations interface
- **InstituteService.java**: Institute operations interface
- **StateService.java**: State operations interface
- **MinistryService.java**: Ministry operations interface
- **FileStorageService.java**: File storage operations interface

### Service Implementations
- **AuthServiceImpl.java**: Authentication logic implementation
- **StudentServiceImpl.java**: Student operations implementation
- **InstituteServiceImpl.java**: Institute operations implementation
- **StateServiceImpl.java**: State operations implementation
- **MinistryServiceImpl.java**: Ministry operations implementation
- **FileStorageServiceImpl.java**: File storage operations implementation

## Developer Task Allocation

### Developer 1: Core & Authentication
- **Files**: SecurityConfig.java, UserDetailsServiceImpl.java, JwtUtil.java, JwtRequestFilter.java, AuthController.java, AuthService.java, AuthServiceImpl.java
- **Tasks**: Project setup, security configuration, JWT implementation, user authentication

### Developer 2: Student Module
- **Files**: StudentController.java, StudentService.java, StudentServiceImpl.java
- **Tasks**: Student operations, scholarship applications, profile management

### Developer 3: Institute Module
- **Files**: InstituteController.java, InstituteService.java, InstituteServiceImpl.java
- **Tasks**: Institute verification workflow, application processing

### Developer 4: State Nodal Officer Module
- **Files**: StateController.java, StateService.java, StateServiceImpl.java
- **Tasks**: State-level verification, institute registration approval

### Developer 5: Ministry Module & File Handling
- **Files**: MinistryController.java, MinistryService.java, MinistryServiceImpl.java, FileStorageService.java, FileStorageServiceImpl.java
- **Tasks**: Final approval workflow, file storage management

## API Endpoints

### Public Endpoints
- `POST /api/auth/register/student` - Student registration
- `POST /api/auth/register/institute` - Institute registration
- `POST /api/auth/login` - User authentication

### Student Endpoints (Role: STUDENT)
- `GET /api/schemes` - View available schemes
- `POST /api/applications` - Submit application
- `GET /api/applications` - View student applications
- `GET /api/student/profile` - View profile
- `PUT /api/student/profile` - Update profile

### Institute Endpoints (Role: INSTITUTE)
- `GET /api/institute/applications/pending` - View pending applications
- `POST /api/institute/applications/{id}/verify` - Verify application
- `POST /api/institute/applications/{id}/reject` - Reject application

### State Officer Endpoints (Role: STATE_OFFICER)
- `GET /api/state/applications/pending` - View pending applications
- `POST /api/state/applications/{id}/approve` - Approve application
- `POST /api/state/applications/{id}/reject` - Reject application
- `GET /api/state/institutes/pending` - View pending institute requests
- `POST /api/state/institutes/{id}/approve` - Approve institute registration

### Ministry Endpoints (Role: MINISTRY)
- `GET /api/ministry/applications/pending` - View pending applications
- `POST /api/ministry/applications/{id}/grant` - Grant scholarship
- `POST /api/ministry/applications/{id}/reject` - Reject application
- `GET /api/ministry/institutes/pending` - View pending institute requests
- `POST /api/ministry/institutes/{id}/approve` - Approve institute registration

## Database Schema

The application uses the following main entities:
1. **User** - Base authentication entity
2. **StudentProfile** - Student-specific information
3. **InstituteProfile** - Institute-specific information
4. **ScholarshipScheme** - Available scholarship schemes
5. **ScholarshipApplication** - Application workflow
6. **ApplicationDocument** - Document management

## Security Features

- JWT-based authentication
- Role-based access control
- Password hashing with BCrypt
- CORS configuration
- Stateless session management

## Next Steps

1. **Database Setup**: Configure PostgreSQL database and run the application
2. **Implementation**: Each developer should implement their assigned modules
3. **Testing**: Test all endpoints with Postman or similar tools
4. **Documentation**: Complete API documentation
5. **Frontend Integration**: Connect with React frontend

## Notes

- All service methods contain detailed TODO comments for developers
- The project follows Spring Boot best practices
- JPA entities include validation annotations
- Repository interfaces extend JpaRepository for basic CRUD operations
- Controllers include proper security annotations
- File upload handling is implemented for document management
