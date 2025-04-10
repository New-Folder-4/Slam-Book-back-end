package com.system.slam.controller;

import com.system.slam.entity.Notification;
import com.system.slam.service.NotificationService;
import com.system.slam.service.SecurityContextService;
import com.system.slam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final UserService userService;
    private final SecurityContextService securityContextService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(
            UserService userService,
            SecurityContextService securityContextService,
            NotificationService notificationService
    ) {
        this.userService = userService;
        this.securityContextService = securityContextService;
        this.notificationService = notificationService;
    }

    @GetMapping("/{idUser}")
    public List<Notification> getNotificationsByUserId(@PathVariable Long idUser) {
        return notificationService.getNotificationsByUserId(idUser);
    }

    @GetMapping
    public List<Notification> getNotificationsByUserId2() {
        return notificationService.getNotificationsByUserId(securityContextService.getCurrentUserId());
    }

    @DeleteMapping("/{idNotification}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long idNotification) {
        try {
            notificationService.deleteNotification(idNotification);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to delete notification"));
        }
    }


}