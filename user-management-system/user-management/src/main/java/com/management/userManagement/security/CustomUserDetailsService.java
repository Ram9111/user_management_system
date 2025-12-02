package com.management.userManagement.security;

import com.management.userManagement.entity.User;
import com.management.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * =====================================================================
     *  Project Name  : Restaurant Inventory System
     *  Class Name    : CustomUserDetailsService
     *  Description   : Custom implementation of Spring Security's UserDetailsService.
     *                  Responsible for loading user-specific data during authentication.
     *
     *  Author        : Ram Choudhary
     *  Created Date  : 30-Nov-2025
     *  Version       : 1.0
     * =====================================================================
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //   Fetch user from DB (your entity)
        User user = userRepository.findByUserName(username);

        //   Return Spring Security's User (not your entity)
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getUserRoleId() != null ? user.getUserRoleId().toString() : "USER") // optional
                .build();
    }
}
