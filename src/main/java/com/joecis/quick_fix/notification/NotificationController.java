package com.joecis.quick_fix.notification;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joecis.quick_fix.DTO.NotificationInfo;

@RestController
public class NotificationController {
    NotificationService notificationService; 

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<NotificationInfo> fetchNotificationsByDestUserId(
            @AuthenticationPrincipal Long id) {
        return this.notificationService.getNotificationsByDestUserId(id);
    }

}
