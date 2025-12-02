package com.management.userManagement.controller;

import com.management.userManagement.entity.User;
import com.management.userManagement.service.UserService;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // optional, allows frontend access
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * Registers a new user into the system.
     *
     * @param user User object containing registration details
     * @return Saved User entity with generated ID and timestamps
     */

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        String email = authentication.getName(); // JWT stores email as subject

        User user = userService.findByEmail(email);

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getUserId());
        response.put("username", user.getUserName());
        response.put("email", user.getUserEmail());
        response.put("roles", user.getUserRoleId()
                .stream()
                .map(com.management.userManagement.entity.Role::getRoleName)
                .toList());



        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}/roles")
    public ResponseEntity<?> assignRoleToUser(
            @PathVariable Long userId,
            @RequestBody Map<String, String> reqBody
    ) {
        String roleName = reqBody.get("role");

        userService.assignRoleToUser(userId, roleName);

        return ResponseEntity.ok("Role assigned successfully!");
    }

}

