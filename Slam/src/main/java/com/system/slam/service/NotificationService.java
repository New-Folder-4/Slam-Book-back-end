package com.system.slam.service;

import com.system.slam.entity.Notification;
import com.system.slam.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;


    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByUserId(Long idUser) {
        return notificationRepository.findByIdUser(idUser);
    }
}