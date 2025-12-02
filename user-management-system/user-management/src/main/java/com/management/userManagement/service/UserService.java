package com.management.userManagement.service;

import com.management.userManagement.entity.User;

public interface UserService {
    User registerUser(User user);
    User findByEmail(String email);
    void assignRoleToUser(Long userId, String roleName);


}
