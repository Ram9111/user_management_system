package com.management.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.management.userManagement.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    boolean existsByUserEmail(String userEmail);
    Optional<User> findByEmail(String email);
}

