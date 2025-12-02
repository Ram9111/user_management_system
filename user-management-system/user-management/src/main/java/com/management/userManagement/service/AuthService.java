package com.management.userManagement.service;

import com.management.userManagement.dto.LoginRequest;
import com.management.userManagement.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
