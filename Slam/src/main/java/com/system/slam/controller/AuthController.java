package com.system.slam.controller;

import com.system.slam.entity.User;
import com.system.slam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user, false, false);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody User user) {
        User registeredStaff = userService.registerUser(user, true, false);
        return ResponseEntity.ok(registeredStaff);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
        User registeredAdmin = userService.registerUser(user, true, true);
        return ResponseEntity.ok(registeredAdmin);
    }
}