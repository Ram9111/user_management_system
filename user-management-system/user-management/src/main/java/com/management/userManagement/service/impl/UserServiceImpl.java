package com.management.userManagement.service.impl;

import com.management.userManagement.entity.Role;
import com.management.userManagement.entity.User;
import com.management.userManagement.exception.DuplicateResourceException;
import com.management.userManagement.repository.RoleRepository;
import com.management.userManagement.repository.UserRepository;
import com.management.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
/**
 * =====================================================================
 *  Project Name  : User Management System
 *  Class Name   : UserServiceImpl
 *  Description   : Register the user details and validate before save in db.
 *
 *  Author        : Ram Choudhary
 *  Created Date  : 30-Nov-2025
 *  Version       : 1.0
 * =====================================================================
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Check for duplicate email
        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            throw new DuplicateResourceException("Email already exists! Please use a different email.");
        }
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // Set audit fields
        user.setCreateDate(LocalDateTime.now());
        user.setActiveFlag(1);
        user.setEnableFlag(1);


        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Transactional
    public void assignRoleToUser(Long userId, String roleName) {

        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByRolename(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getUserRoleId().add(role); // Add role to user

        userRepository.save(user);
    }
}
