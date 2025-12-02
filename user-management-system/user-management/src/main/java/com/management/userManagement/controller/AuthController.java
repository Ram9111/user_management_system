package com.management.userManagement.controller;

import com.management.userManagement.dto.LoginRequest;
import com.management.userManagement.dto.LoginResponse;
import com.management.userManagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthService authService;
    /**
     * Handles user login and returns user details along with authentication status.
     *
     * @param request Login credentials (username and password)
     * @return LoginResponse containing status, message, and user details if successful
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

