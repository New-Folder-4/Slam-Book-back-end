package com.system.slam.service;

import com.system.slam.web.dto.AuthResponseDto;
import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.web.dto.UserUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProfileService {
    private final UserService userService;
    private final SecurityContextService securityContextService;

    public ProfileService(UserService userService,
                          SecurityContextService securityContextService) {
        this.userService = userService;
        this.securityContextService = securityContextService;
    }

    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserProfileDto profile = userService.findProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }

    public ResponseEntity<?> updateCurrentUserProfile(UserUpdateDto userUpdateDto) {
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