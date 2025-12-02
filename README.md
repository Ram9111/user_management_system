API Documentation – Introduction
Purpose of the Document
This API Documentation provides a concise technical reference for all backend services developed for the User Management System.
It is created to help frontend developers, testers, and integration teams understand how to securely interact with the backend through RESTful APIs.
The document includes API URLs, HTTP methods, request/response formats, and their specific purposes.

Scope of the APIs
This document covers the core APIs required for user identity and access management:
• User Registration – Create new user accounts with encrypted passwords
• User Login – Validate credentials and generate JWT tokens
• View Current User – Retrieve logged-in user details using token
• Role Management – Create roles (ADMIN/USER)
• Assign Roles – Associate roles with users
• Admin Dashboard – Admin-only statistics endpoint



Register User API 

http://localhost:8080/api/users/register
Method: POST
Request
{
  "userName": "ram2",
  "password": "123",
  "name": "Ram Choudhary",
  "userEmail": "ram1123@test.com",
  "userRoleId": 2
}

Response
200 OK
{
    "userId": 13,
    "userName": "ram2",
    "password": "$2a$10$WuqfpS5s2bTpMksZai2tsekf1MtaqxXdtDoVPXjgJU0szc341lgoW",
    "userEmail": "ram1123@test.com",
    "userRoleId": 2,
    "createDate": "2025-12-01T20:29:08.610068",
    "modifyDate": null,
    "createUserId": null,
    "modifyUserId": null,
    "activeFlag": 1,
    "enableFlag": 1
}


If validate the email then Response will be
{
    "status": 409,
    "message": "Email already exists! Please use a different email.",
    "data": null
}

Purpose of This API
The User Registration API (POST /api/users/register) is used to create a new user account in the system.
It allows onboarding processes to add new users by capturing essential profile details such as username, password, name.
Once the request is submitted, the API validates the input, encrypts the password, creates a new entry in the users table, and returns the complete saved user data with the generated userId.



Login User API

http://localhost:8080/api/users/login
Method: POST
Request
{
  "username": "ram",
  "password": "test123"
}
Response
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW0yIiwiaWF0IjoxNzYxOTczMzAxLCJleHAiOjE3NjE5NzY5MDF9.-ht7H8zg4CtxI5mAE-Rq-xAu_X_XU-DgRcbxtFUDwMQ",
    "username": "ram",
    "message": "Login successful",
    "loginTime": "2025-11-01T10:31:41.6347203"
}

Purpose of This API
The Login API (POST /api/users/login) is used to authenticate a user by validating their username and password. Upon successful verification, the API generates a secure JWT token that the client must use for accessing protected resources throughout the system. This API ensures secure user access, prevents unauthorized usage, and logs essential login details such as the username and login timestamp

View Current User Profile
http://localhost:8080/api/users/me
Method: GET
Request: Authorization: Bearer <your-jwt-token>


Response
{
  "id": 3,
  "username": "ram",
  "email": "ram@gmail.com",
  "roles": [
    "ADMIN",
    "USER"
  ]
}

Create Role (Admin Only)

http://localhost:8080/api/roles
Method: POST

Request
{
  "rolename": "ADMIN"
}

Response
{
  "roleId": 1,
  "rolename": "ADMIN"
}

Admin Dashboard Stats (Admin Only)

http://localhost:8080/api/admin/stats
Method: GET

Request
Authorization: Bearer <ADMIN-jwt-token>
Response
{
  "totalUsers": 53,
  "lastLoginExample": "2025-01-15 10:30 AM",
  "activeAdmins": 2,
  "activeUsers": 50
}
