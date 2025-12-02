package com.management.userManagement.service.impl;

import com.management.userManagement.dto.LoginRequest;
import com.management.userManagement.dto.LoginResponse;
import com.management.userManagement.entity.User;
import com.management.userManagement.repository.UserRepository;
import com.management.userManagement.security.JwtUtil;
import com.management.userManagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * =====================================================================
 *  Project Name  : User Management System
 *  Class Name   : AuthServiceImpl
 *  Description   : Authenticates the user credentials, validates password,
 *                  and generates a JWT token upon successful login.
 *
 *  Author        : Ram Choudhary
 *  Created Date  : 30-Nov-2025
 *  Version       : 1.0
 * =====================================================================
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUserName(request.getUsername());

        if (user == null) {
            throw new RuntimeException("Invalid username");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUserName());
        return new LoginResponse(token, user.getUserName(), "Login successful", LocalDateTime.now());
    }
}
