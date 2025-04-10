package com.system.slam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.entity.User;
import com.system.slam.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/test")
    public ResponseEntity<String> getUserTest() {
        return ResponseEntity.ok("This is user test");
    }


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    private Long getCurrentUserId() {
        return 1L; // тест
    }
}

