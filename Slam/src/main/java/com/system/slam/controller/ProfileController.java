package com.system.slam.controller;

import com.system.slam.service.SecurityContextService;
import com.system.slam.service.UserService;
import com.system.slam.web.dto.AuthResponseDto;
import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.web.dto.UserUpdateDto;
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
    private final SecurityContextService securityContextService;

       @Autowired
    public ProfileController(UserService userService,
                             SecurityContextService securityContextService) {
        this.userService = userService;
        this.securityContextService = securityContextService;
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

        Long currentUserId = securityContextService.getCurrentUserId();

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