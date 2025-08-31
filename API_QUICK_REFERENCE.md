# 🚀 API Quick Reference Card

## 🔑 Essential Information
- **Base URL:** `http://localhost:8080`
- **Authentication:** JWT Bearer Token
- **Content-Type:** `application/json` (except file uploads)

## 📍 Key Endpoints

### 🔐 Public Endpoints (No Auth)
```
POST /api/auth/register/student    - Student registration
POST /api/auth/register/institute  - Institute registration  
POST /api/auth/login               - User login
GET  /api/test/health             - Health check
GET  /swagger-ui.html             - API documentation
```

### 👨‍🎓 Student Endpoints (STUDENT role)
```
GET  /api/student/profile         - Get profile
PUT  /api/student/profile         - Update profile
GET  /api/schemes                 - Get scholarship schemes
GET  /api/applications            - Get my applications
POST /api/applications            - Submit application
```

### 🏫 Institute Endpoints (INSTITUTE role)
```
GET  /api/institute/applications/pending     - Get pending apps
POST /api/institute/applications/{id}/verify - Verify app
POST /api/institute/applications/{id}/reject - Reject app
```

### 🏛️ State Endpoints (STATE_OFFICER role)
```
GET  /api/state/institutes/pending           - Pending institutes
POST /api/state/institutes/{id}/approve      - Approve institute
GET  /api/state/applications/pending         - Pending applications
POST /api/state/applications/{id}/approve    - Approve application
POST /api/state/applications/{id}/reject     - Reject application
```

### 🏛️ Ministry Endpoints (MINISTRY role)
```
GET  /api/ministry/institutes/pending       - Pending institutes
POST /api/ministry/institutes/{id}/approve  - Approve institute
GET  /api/ministry/applications/pending     - Pending applications
POST /api/ministry/applications/{id}/grant  - Grant scholarship
POST /api/ministry/applications/{id}/reject - Reject application
```

## 🔒 Authentication Headers
```javascript
// For protected endpoints
headers: {
  'Authorization': 'Bearer ' + jwtToken,
  'Content-Type': 'application/json'
}
```

## 📊 Response Formats

### Success Response
```json
{
  "token": "jwt_token_here",
  "message": "Operation successful",
  "userId": 123,
  "email": "user@example.com",
  "role": "STUDENT",
  "name": "User Name"
}
```

### Error Response
```json
{
  "error": "Error Type",
  "message": "Detailed error message"
}
```

## 🚨 HTTP Status Codes
- **200:** Success
- **201:** Created
- **400:** Bad Request (validation error)
- **401:** Unauthorized (missing/invalid token)
- **403:** Forbidden (insufficient permissions)
- **404:** Not Found
- **500:** Internal Server Error

## 💡 Quick Examples

### Login & Get Token
```javascript
const login = async (email, password) => {
  const response = await fetch('/api/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password })
  });
  const data = await response.json();
  localStorage.setItem('token', data.token);
  return data;
};
```

### Authenticated Request
```javascript
const getProfile = async () => {
  const token = localStorage.getItem('token');
  const response = await fetch('/api/student/profile', {
    headers: { 'Authorization': `Bearer ${token}` }
  });
  return await response.json();
};
```

### File Upload
```javascript
const submitApp = async (appData, files) => {
  const token = localStorage.getItem('token');
  const formData = new FormData();
  formData.append('application', JSON.stringify(appData));
  files.forEach(file => formData.append('documents', file));
  
  const response = await fetch('/api/applications', {
    method: 'POST',
    headers: { 'Authorization': `Bearer ${token}` },
    body: formData
  });
  return await response.json();
};
```

## 🔧 Development Tips
1. **Always check response status** before parsing JSON
2. **Store JWT token** in localStorage after login
3. **Handle 401 errors** by redirecting to login
4. **Use FormData** for file uploads
5. **Test with Swagger UI** at `/swagger-ui.html`

## 📱 Frontend Framework Examples

### React
```javascript
const [token, setToken] = useState(localStorage.getItem('token'));

useEffect(() => {
  if (token) {
    // Make authenticated requests
  }
}, [token]);
```

### Vue.js
```javascript
const token = ref(localStorage.getItem('token'));

const apiCall = async () => {
  const response = await fetch('/api/endpoint', {
    headers: { 'Authorization': `Bearer ${token.value}` }
  });
};
```

### Angular
```typescript
const token = localStorage.getItem('token');

const apiCall = () => {
  return this.http.get('/api/endpoint', {
    headers: { 'Authorization': `Bearer ${token}` }
  });
};
```

---

*Keep this card handy during development! 🎯*
