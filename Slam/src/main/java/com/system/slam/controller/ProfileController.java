package com.system.slam.controller;

import com.system.slam.service.UserService;
import com.system.slam.web.dto.AuthResponseDto;
import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.web.dto.UserUpdateDto;
import com.system.slam.web.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    private final UserService userService;

       @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserProfileDto profile = userService.findProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }
    @PutMapping
    public ResponseEntity<?> updateCurrentUserProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();

        try {

            AuthResponseDto updatedUser = userService.updateUserProfile(userUpdateDto, currentUserId);
            return ResponseEntity.ok(updatedUser);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", "Failed to update user profile"));
        }
    }

}