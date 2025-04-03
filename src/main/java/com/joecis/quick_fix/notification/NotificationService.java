package com.joecis.quick_fix.notification;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return this.notificationRepository.findAll();
    }
    public List<Notification> getNotificationsByDestUserId(Long id) {
       return this.notificationRepository.findAllByDestUserId(id);
    }

}
