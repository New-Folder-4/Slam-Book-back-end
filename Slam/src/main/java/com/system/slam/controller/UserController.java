package com.system.slam.controller;

import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.entity.User;
import com.system.slam.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserProfileDto getProfile() {
        Long userId = getCurrentUserId();
        return userService.getUserProfile(userId);
    }

    @PutMapping("/profile")
    public UserProfileDto updateProfile(@RequestBody UserProfileDto dto) {
        Long userId = getCurrentUserId();
        User updatedUser = userService.updateUserProfile(userId, dto);
        return userService.convertToUserProfileDto(updatedUser);
    }

    private Long getCurrentUserId() {
        return 1L; // тест
    }
}
