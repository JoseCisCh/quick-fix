package com.joecis.quick_fix.notification;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    NotificationService notificationService; 

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<Notification> fetchAllNotifications() {
        return this.notificationService.getAllNotifications();
    }

    @GetMapping("/notifications/{id}")
    public List<Notification> fetchNotificationsByDestUserId(@PathVariable Long id, Principal principal) {
        return this.notificationService.getNotificationsByDestUserId(id);
    }

}
