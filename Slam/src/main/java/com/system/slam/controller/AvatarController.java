package com.system.slam.controller;

import com.system.slam.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader) {
        try {
            return avatarService.uploadAvatar(file, authHeader);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/default/{avatarId}")
    public ResponseEntity<String> setDefaultAvatar(
            @PathVariable int avatarId,
            @RequestHeader("Authorization") String authHeader) {
        try {
            return avatarService.setDefaultAvatar(avatarId, authHeader);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAvatar(@PathVariable Long userId) {
        try {
            return avatarService.getAvatar(userId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/default/all")
    public ResponseEntity<?> getAllDefaultAvatars() {
        try {
            return avatarService.getAllDefaultAvatars();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}