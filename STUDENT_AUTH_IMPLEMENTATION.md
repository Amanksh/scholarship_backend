# Student Registration and Login Implementation

## Overview
This document describes the implementation of student registration and login functionality for the National Scholarship Portal.

## Implemented Features

### 1. Student Registration (`POST /api/auth/register/student`)
- **Endpoint**: `POST /api/auth/register/student`
- **Description**: Registers a new student user with profile creation
- **Request Body**: `StudentRegistrationRequest` DTO
- **Features**:
  - Email uniqueness validation
  - Aadhar number uniqueness validation
  - Mobile number uniqueness validation
  - Password hashing using BCrypt
  - Automatic User and StudentProfile entity creation
  - Transactional operation for data consistency

### 2. Student Login (`POST /api/auth/login`)
- **Endpoint**: `POST /api/auth/login`
- **Description**: Authenticates student and returns JWT token
- **Request Body**: `LoginRequest` DTO
- **Features**:
  - Credential validation using Spring Security
  - JWT token generation
  - User role and profile information in response

### 3. Institute Registration (`POST /api/auth/register/institute`)
- **Endpoint**: `POST /api/auth/register/institute`
- **Description**: Registers a new institute (pending approval)
- **Request Body**: `InstituteRegistrationRequest` DTO
- **Features**:
  - Email and institute code uniqueness validation
  - Password hashing
  - Institute profile creation (not approved initially)

## API Endpoints

### Student Registration
```http
POST /api/auth/register/student
Content-Type: application/json

{
  "email": "student@example.com",
  "password": "password123",
  "mobileNumber": "9876543210",
  "name": "John Doe",
  "dateOfBirth": "2000-01-01",
  "gender": "Male",
  "domicileState": "Maharashtra",
  "aadharNumber": "123456789012",
  "fatherName": "Father Name",
  "motherName": "Mother Name",
  "category": "General",
  "religion": "Hindu",
  "address": "123 Main St",
  "district": "Mumbai",
  "pincode": "400001"
}
```

### Student Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "student@example.com",
  "password": "password123"
}
```

### Institute Registration
```http
POST /api/auth/register/institute
Content-Type: application/json

{
  "email": "institute@example.com",
  "password": "password123",
  "mobileNumber": "9876543210",
  "instituteName": "ABC Institute",
  "instituteCode": "ABC001",
  "diseCode": "DISE123",
  "address": "456 Institute St",
  "district": "Mumbai",
  "state": "Maharashtra",
  "pincode": "400002",
  "contactPersonName": "Contact Person",
  "contactPersonMobile": "9876543210",
  "contactPersonEmail": "contact@institute.com",
  "instituteType": "Private",
  "affiliationBody": "CBSE",
  "establishmentYear": "1990"
}
```

## Response Format

### Success Response
```json
{
  "token": "jwt_token_here",
  "message": "Student registered successfully",
  "userId": 1,
  "email": "student@example.com",
  "role": "STUDENT",
  "name": "John Doe"
}
```

### Error Response
```json
{
  "message": "Email already registered"
}
```

## Security Features

1. **Password Hashing**: BCrypt password encoder
2. **JWT Authentication**: Secure token-based authentication
3. **Role-based Access Control**: Different roles (STUDENT, INSTITUTE, STATE_OFFICER, MINISTRY)
4. **Input Validation**: Jakarta validation constraints
5. **CORS Configuration**: Cross-origin request support

## Database Entities

### User Entity
- Base entity for all users
- Contains authentication information (email, password, mobile, role)
- One-to-one relationship with profile entities

### StudentProfile Entity
- Extends User functionality
- Contains student-specific information
- Linked to User via foreign key

### InstituteProfile Entity
- Extends User functionality
- Contains institute-specific information
- Approval status tracking

## Testing the Implementation

### 1. Start the Application
```bash
mvn spring-boot:run
```

### 2. Access Swagger UI
- Navigate to: `http://localhost:8080/swagger-ui.html`
- Test the endpoints using the interactive interface

### 3. Test Student Registration
```bash
curl -X POST "http://localhost:8080/api/auth/register/student" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "teststudent@example.com",
    "password": "password123",
    "mobileNumber": "9876543210",
    "name": "Test Student",
    "dateOfBirth": "2000-01-01",
    "gender": "Male",
    "domicileState": "Maharashtra",
    "aadharNumber": "123456789012"
  }'
```

### 4. Test Student Login
```bash
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "teststudent@example.com",
    "password": "password123"
  }'
```

## Next Steps

After implementing student registration and login, the next entities to implement are:

1. **ScholarshipScheme**: Define available scholarships
2. **ScholarshipApplication**: Student applications for scholarships
3. **ApplicationDocument**: Supporting documents for applications
4. **State and Ministry Services**: Administrative functionality

## Technical Notes

- **Transaction Management**: All registration operations are transactional
- **Error Handling**: Comprehensive error handling with meaningful messages
- **Validation**: Input validation using Jakarta validation framework
- **Security**: Spring Security with JWT implementation
- **Database**: PostgreSQL with JPA/Hibernate
