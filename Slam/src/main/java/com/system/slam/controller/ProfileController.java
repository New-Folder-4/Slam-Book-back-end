package com.system.slam.controller;

import com.system.slam.service.ProfileService;
import com.system.slam.web.dto.UserProfileDto;
import com.system.slam.web.dto.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        return profileService.getCurrentUserProfile();
    }


    @PutMapping
    public ResponseEntity<?> updateCurrentUserProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        return profileService.updateCurrentUserProfile(userUpdateDto);
    }
}