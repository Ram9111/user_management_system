package com.management.userManagement.repository;

import com.management.userManagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRolename(String rolename);

    Optional<Role> findByRolename(String rolename);

}
