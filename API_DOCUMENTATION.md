# National Scholarship Portal API Documentation

## Overview
This document provides comprehensive information about all available API endpoints for the National Scholarship Portal. Use this documentation to build your frontend application.

**Base URL:** `http://localhost:8080`  
**API Version:** v1  
**Authentication:** JWT Bearer Token (for protected endpoints)

---

## 🔐 Authentication Endpoints

### 1. Student Registration
**Endpoint:** `POST /api/auth/register/student`  
**Description:** Registers a new student user with profile information  
**Access:** Public (No authentication required)

**Request Body:**
```json
{
  "email": "string (required, unique)",
  "password": "string (required, min 8 characters)",
  "mobileNumber": "string (required, 10 digits, unique)",
  "name": "string (required)",
  "dateOfBirth": "string (required, format: YYYY-MM-DD, must be in past)",
  "gender": "string (required)",
  "domicileState": "string (required)",
  "aadharNumber": "string (required, 12 digits, unique)",
  "fatherName": "string (optional)",
  "motherName": "string (optional)",
  "category": "string (optional)",
  "religion": "string (optional)",
  "address": "string (optional)",
  "district": "string (optional)",
  "pincode": "string (optional)"
}
```

**Response:**
```json
{
  "token": "string (JWT token)",
  "message": "string (success/error message)",
  "userId": "number (user ID)",
  "email": "string (user email)",
  "role": "string (user role)",
  "name": "string (user name)"
}
```

**Example Request:**
```bash
curl -X POST "http://localhost:8080/api/auth/register/student" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "student@example.com",
    "password": "password123",
    "mobileNumber": "9876543210",
    "name": "John Doe",
    "dateOfBirth": "2000-01-01",
    "gender": "Male",
    "domicileState": "Maharashtra",
    "aadharNumber": "123456789012"
  }'
```

---

### 2. Institute Registration
**Endpoint:** `POST /api/auth/register/institute`  
**Description:** Submits an institute registration request  
**Access:** Public (No authentication required)

**Request Body:**
```json
{
  "email": "string (required, unique)",
  "password": "string (required, min 8 characters)",
  "mobileNumber": "string (required, 10 digits, unique)",
  "instituteName": "string (required)",
  "instituteCode": "string (required, unique)",
  "diseCode": "string (optional)",
  "address": "string (optional)",
  "district": "string (optional)",
  "state": "string (optional)",
  "pincode": "string (optional)",
  "contactPersonName": "string (optional)",
  "contactPersonMobile": "string (optional)",
  "contactPersonEmail": "string (optional)",
  "instituteType": "string (optional)",
  "affiliationBody": "string (optional)",
  "establishmentYear": "string (optional)"
}
```

**Response:** Same format as student registration

---

### 3. User Login
**Endpoint:** `POST /api/auth/login`  
**Description:** Authenticates user and returns JWT token  
**Access:** Public (No authentication required)

**Request Body:**
```json
{
  "email": "string (required)",
  "password": "string (required)"
}
```

**Response:**
```json
{
  "token": "string (JWT token for authentication)",
  "message": "string (success/error message)",
  "userId": "number (user ID)",
  "email": "string (user email)",
  "role": "string (user role)",
  "name": "string (user name)"
}
```

**Example Request:**
```bash
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "student@example.com",
    "password": "password123"
  }'
```

---

## 👨‍🎓 Student Endpoints (Require JWT Authentication)

**Authentication Header:** `Authorization: Bearer <JWT_TOKEN>`

### 4. Get Student Profile
**Endpoint:** `GET /api/student/profile`  
**Description:** Retrieves the logged-in student's profile information  
**Access:** STUDENT role required

**Response:**
```json
{
  "id": "number",
  "user": {
    "id": "number",
    "email": "string",
    "mobileNumber": "string",
    "role": "string"
  },
  "name": "string",
  "dateOfBirth": "string",
  "gender": "string",
  "domicileState": "string",
  "aadharNumber": "string",
  "fatherName": "string",
  "motherName": "string",
  "category": "string",
  "religion": "string",
  "address": "string",
  "district": "string",
  "pincode": "string",
  "bankAccountNumber": "string",
  "bankName": "string",
  "ifscCode": "string"
}
```

---

### 5. Update Student Profile
**Endpoint:** `PUT /api/student/profile`  
**Description:** Updates the logged-in student's profile information  
**Access:** STUDENT role required

**Request Body:**
```json
{
  "name": "string (optional)",
  "dateOfBirth": "string (optional, format: YYYY-MM-DD)",
  "gender": "string (optional)",
  "domicileState": "string (optional)",
  "fatherName": "string (optional)",
  "motherName": "string (optional)",
  "category": "string (optional)",
  "religion": "string (optional)",
  "address": "string (optional)",
  "district": "string (optional)",
  "pincode": "string (optional, 6 digits)",
  "bankAccountNumber": "string (optional)",
  "bankName": "string (optional)",
  "ifscCode": "string (optional)"
}
```

**Response:** Updated profile object

---

### 6. Get All Scholarship Schemes
**Endpoint:** `GET /api/schemes`  
**Description:** Retrieves all available scholarship schemes  
**Access:** STUDENT role required

**Response:**
```json
[
  {
    "id": "number",
    "schemeName": "string",
    "description": "string",
    "eligibilityCriteria": "string",
    "scholarshipAmount": "number",
    "applicationStartDate": "string (YYYY-MM-DD)",
    "applicationEndDate": "string (YYYY-MM-DD)",
    "isActive": "boolean"
  }
]
```

---

### 7. Submit Scholarship Application
**Endpoint:** `POST /api/applications`  
**Description:** Submits a new scholarship application with documents  
**Access:** STUDENT role required

**Request Body:** Multipart Form Data
```
application: JSON string containing application details
documents: Array of files (optional)
```

**Application JSON Structure:**
```json
{
  "schemeId": "number (required)",
  "additionalNotes": "string (optional)",
  "familyIncome": "string (optional)",
  "academicYear": "string (optional)",
  "currentSemester": "string (optional)",
  "cgpa": "string (optional)",
  "attendancePercentage": "string (optional)"
}
```

**Response:**
```json
{
  "message": "string (success/error message)"
}
```

---

### 8. Get Student Applications
**Endpoint:** `GET /api/applications`  
**Description:** Retrieves all applications submitted by the logged-in student  
**Access:** STUDENT role required

**Response:**
```json
[
  {
    "id": "number",
    "scheme": {
      "id": "number",
      "schemeName": "string"
    },
    "applicationDate": "string",
    "status": "string",
    "documents": [
      {
        "id": "number",
        "documentType": "string",
        "fileName": "string",
        "uploadDate": "string"
      }
    ]
  }
]
```

---

## 🏫 Institute Endpoints (Require JWT Authentication)

**Authentication Header:** `Authorization: Bearer <JWT_TOKEN>`

### 9. Get Pending Applications
**Endpoint:** `GET /api/institute/applications/pending`  
**Description:** Retrieves pending applications for institute verification  
**Access:** INSTITUTE role required

### 10. Verify Application
**Endpoint:** `POST /api/institute/applications/{appId}/verify`  
**Description:** Verifies a student's scholarship application  
**Access:** INSTITUTE role required

### 11. Reject Application
**Endpoint:** `POST /api/institute/applications/{appId}/reject`  
**Description:** Rejects a student's scholarship application  
**Access:** INSTITUTE role required

---

## 🏛️ State Officer Endpoints (Require JWT Authentication)

**Authentication Header:** `Authorization: Bearer <JWT_TOKEN>`

### 12. Get Pending Institute Requests
**Endpoint:** `GET /api/state/institutes/pending`  
**Description:** Retrieves pending institute registration requests  
**Access:** STATE_OFFICER role required

### 13. Approve Institute Registration
**Endpoint:** `POST /api/state/institutes/{regId}/approve`  
**Description:** Approves an institute registration request  
**Access:** STATE_OFFICER role required

### 14. Get Pending Applications
**Endpoint:** `GET /api/state/applications/pending`  
**Description:** Retrieves pending applications for state approval  
**Access:** STATE_OFFICER role required

### 15. Approve Application
**Endpoint:** `POST /api/state/applications/{appId}/approve`  
**Description:** Approves a scholarship application at state level  
**Access:** STATE_OFFICER role required

### 16. Reject Application
**Endpoint:** `POST /api/state/applications/{appId}/reject`  
**Description:** Rejects a scholarship application at state level  
**Access:** STATE_OFFICER role required

---

## 🏛️ Ministry Endpoints (Require JWT Authentication)

**Authentication Header:** `Authorization: Bearer <JWT_TOKEN>`

### 17. Get Pending Institute Requests
**Endpoint:** `GET /api/ministry/institutes/pending`  
**Description:** Retrieves pending institute registration requests for ministry approval  
**Access:** MINISTRY role required

### 18. Approve Institute Registration
**Endpoint:** `POST /api/ministry/institutes/{regId}/approve`  
**Description:** Approves an institute registration request at ministry level  
**Access:** MINISTRY role required

### 19. Get Pending Applications
**Endpoint:** `GET /api/ministry/applications/pending`  
**Description:** Retrieves pending applications for ministry approval  
**Access:** MINISTRY role required

### 20. Grant Scholarship
**Endpoint:** `POST /api/ministry/applications/{appId}/grant`  
**Description:** Grants final approval for a scholarship application  
**Access:** MINISTRY role required

### 21. Reject Application
**Endpoint:** `POST /api/ministry/applications/{appId}/reject`  
**Description:** Rejects a scholarship application at ministry level  
**Access:** MINISTRY role required

---

## 📚 Public Endpoints (No Authentication Required)

### 22. Health Check
**Endpoint:** `GET /api/test/health`  
**Description:** Simple health check endpoint  
**Response:** `"National Scholarship Portal API is running!"`

### 23. Public Info
**Endpoint:** `GET /api/test/info`  
**Description:** Public information endpoint  
**Response:** Public information about the API

### 24. Swagger UI
**Endpoint:** `GET /swagger-ui.html`  
**Description:** Interactive API documentation interface

### 25. OpenAPI Specification
**Endpoint:** `GET /v3/api-docs`  
**Description:** OpenAPI specification in JSON format

---

## 🔒 Authentication & Authorization

### JWT Token Format
- **Type:** Bearer Token
- **Header:** `Authorization: Bearer <JWT_TOKEN>`
- **Expiration:** 24 hours from generation

### Role-Based Access Control
- **STUDENT:** Can access student-specific endpoints
- **INSTITUTE:** Can access institute-specific endpoints
- **STATE_OFFICER:** Can access state-level endpoints
- **MINISTRY:** Can access ministry-level endpoints

### Error Responses
**401 Unauthorized:**
```json
{
  "error": "Unauthorized",
  "message": "Invalid or missing JWT token"
}
```

**403 Forbidden:**
```json
{
  "error": "Forbidden",
  "message": "Insufficient permissions for this endpoint"
}
```

**400 Bad Request:**
```json
{
  "error": "Bad Request",
  "message": "Validation failed",
  "details": [
    {
      "field": "string",
      "message": "string"
    }
  ]
}
```

---

## 📝 Frontend Integration Examples

### 1. User Registration Flow
```javascript
// Student Registration
const registerStudent = async (studentData) => {
  try {
    const response = await fetch('/api/auth/register/student', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(studentData)
    });
    
    if (response.ok) {
      const result = await response.json();
      // Store JWT token
      localStorage.setItem('token', result.token);
      return result;
    } else {
      throw new Error('Registration failed');
    }
  } catch (error) {
    console.error('Error:', error);
  }
};
```

### 2. Authenticated Request Flow
```javascript
// Get Student Profile
const getStudentProfile = async () => {
  try {
    const token = localStorage.getItem('token');
    const response = await fetch('/api/student/profile', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    if (response.ok) {
      return await response.json();
    } else if (response.status === 401) {
      // Token expired, redirect to login
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
  } catch (error) {
    console.error('Error:', error);
  }
};
```

### 3. File Upload Flow
```javascript
// Submit Application with Documents
const submitApplication = async (applicationData, documents) => {
  try {
    const token = localStorage.getItem('token');
    const formData = new FormData();
    
    formData.append('application', JSON.stringify(applicationData));
    
    if (documents) {
      documents.forEach(doc => {
        formData.append('documents', doc);
      });
    }
    
    const response = await fetch('/api/applications', {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`
      },
      body: formData
    });
    
    if (response.ok) {
      return await response.json();
    }
  } catch (error) {
    console.error('Error:', error);
  }
};
```

---

## 🚀 Getting Started

### 1. Start the Backend
```bash
mvn spring-boot:run
```

### 2. Test the API
```bash
# Health check
curl http://localhost:8080/api/test/health

# Register a student
curl -X POST "http://localhost:8080/api/auth/register/student" \
  -H "Content-Type: application/json" \
  -d @test_student.json
```

### 3. Access Swagger UI
Open `http://localhost:8080/swagger-ui.html` in your browser for interactive API testing.

---

## 📋 Data Validation Rules

### Student Registration
- **Email:** Must be unique, valid email format
- **Password:** Minimum 8 characters
- **Mobile Number:** Exactly 10 digits, must be unique
- **Aadhar Number:** Exactly 12 digits, must be unique
- **Date of Birth:** Must be in the past (YYYY-MM-DD format)

### Institute Registration
- **Email:** Must be unique, valid email format
- **Password:** Minimum 8 characters
- **Mobile Number:** Exactly 10 digits, must be unique
- **Institute Code:** Must be unique

---

## 🔧 Troubleshooting

### Common Issues
1. **403 Forbidden:** Check if user has the required role
2. **401 Unauthorized:** JWT token is missing, expired, or invalid
3. **400 Bad Request:** Validation errors in request data
4. **500 Internal Server Error:** Backend server error

### Debug Steps
1. Check if the backend is running on port 8080
2. Verify JWT token is valid and not expired
3. Ensure request format matches API specification
4. Check server logs for detailed error information

---

## 📞 Support

For technical support or questions about the API:
- **Email:** dev@nsp.gov.in
- **Documentation:** https://nsp.gov.in/docs
- **GitHub Issues:** Report bugs and feature requests

---

*Last Updated: August 31, 2025*  
*API Version: 1.0.0*
