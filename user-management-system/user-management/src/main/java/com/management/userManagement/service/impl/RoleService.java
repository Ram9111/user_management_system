package com.management.userManagement.service.impl;

import com.management.userManagement.entity.Role;
import com.management.userManagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {

        if (roleRepository.existsByRolename(role.getRoleName())) {
            throw new RuntimeException("Role already exists!");
        }

        return roleRepository.save(role);
    }
}
