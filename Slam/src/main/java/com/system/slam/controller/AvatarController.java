package com.system.slam.controller;

import com.system.slam.entity.User;
import com.system.slam.service.UserService;
import com.system.slam.web.security.JwtTokenProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private static final int DEFAULT_AVATARS_COUNT = 15;
    private final Path customAvatarsLocation;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".png", ".jpg", ".jpeg", ".webp");
    private static final Map<String, MediaType> EXTENSION_TO_MEDIA_TYPE = Map.of(
            ".png", MediaType.IMAGE_PNG,
            ".jpg", MediaType.IMAGE_JPEG,
            ".jpeg", MediaType.IMAGE_JPEG,
            ".webp", MediaType.parseMediaType("image/webp")
    );

    public AvatarController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customAvatarsLocation = Paths.get("src/main/resources/static/avatars/custom").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.customAvatarsLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create avatars directory", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader) {

        try {

            String token = authHeader.substring(7); // Удаляем "Bearer "
            String username = jwtTokenProvider.getUsernameFromToken(token);

            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                return ResponseEntity.badRequest().body("Invalid file name");
            }

            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                return ResponseEntity.badRequest().body("Unsupported file format");
            }

            if (file.getSize() > 2 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("File size exceeds 2MB limit");
            }

            String filename = user.getIdUser() + extension;
            Path targetLocation = customAvatarsLocation.resolve(filename);

            if (user.getAvatarPath() != null && !user.getAvatarPath().startsWith("default/")) {
                Files.deleteIfExists(customAvatarsLocation.resolve(user.getAvatarPath()));
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            user.setAvatarPath(filename);
            userService.saveUser(user);

            return ResponseEntity.ok("Custom avatar uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/default/{avatarId}")
    public ResponseEntity<String> setDefaultAvatar(
            @PathVariable int avatarId,
            @RequestHeader("Authorization") String authHeader) {

        try {
            String token = authHeader.substring(7);
            String username = jwtTokenProvider.getUsernameFromToken(token);

            if (avatarId < 1 || avatarId > DEFAULT_AVATARS_COUNT) {
                return ResponseEntity.badRequest().body("Invalid avatar ID");
            }

            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getAvatarPath() != null && !user.getAvatarPath().startsWith("default/")) {
                Files.deleteIfExists(customAvatarsLocation.resolve(user.getAvatarPath()));
            }

            user.setAvatarPath("default/" + avatarId);
            userService.saveUser(user);

            return ResponseEntity.ok("Default avatar uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Resource> getAvatar(@PathVariable Long userId) {
        try {
            System.out.println("Fetching avatar for user ID: " + userId);
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("User not found");
                return ResponseEntity.notFound().build();
            }
            if (user.getAvatarPath() == null) {
                System.out.println("Avatar path is null for user: " + user.getUserName());
                return ResponseEntity.notFound().build();
            }

            Resource resource;
            String avatarPath = user.getAvatarPath();

            if (avatarPath.startsWith("default/")) {
                int avatarId = Integer.parseInt(avatarPath.substring(8));
                resource = new ClassPathResource("static/avatars/default/" + avatarId + ".png");
            } else {
                Path filePath = customAvatarsLocation.resolve(avatarPath).normalize();
                resource = new UrlResource(filePath.toUri());
            }

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Определение Content-Type автоматом
            String extension = avatarPath.contains(".") ?
                    avatarPath.substring(avatarPath.lastIndexOf(".")).toLowerCase() : ".png";
            MediaType mediaType = EXTENSION_TO_MEDIA_TYPE.getOrDefault(extension, MediaType.IMAGE_JPEG);

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/default/all")
    public ResponseEntity<List<Map<String, Object>>> getAllDefaultAvatars() throws IOException {
        List<Map<String, Object>> avatars = new ArrayList<>();

        for (int i = 1; i <= DEFAULT_AVATARS_COUNT; i++) {
            try {
                Resource resource = new ClassPathResource("static/avatars/default/" + i + ".png");
                if (!resource.exists()) continue;

                byte[] imageBytes = Files.readAllBytes(Paths.get(resource.getURI()));
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                String imageSrc = "data:image/png;base64," + base64Image;

                avatars.add(Map.of(
                        "id", i,
                        "imageData", imageSrc
                ));
            } catch (Exception e) {
                System.out.println("Error processing avatar " + i + ": " + e.getMessage());
            }
        }

        return ResponseEntity.ok(avatars);
    }

}