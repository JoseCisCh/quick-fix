package com.joecis.quick_fix.notification;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.DTO.NotificationInfo;

@Service
public class NotificationService {

    NotificationRepository notificationRepository;
    ModelMapper modelMapper;

    public NotificationService(
            NotificationRepository notificationRepository,
            ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }
    public List<Notification> getAllNotifications() {
        return this.notificationRepository.findAll();
    }
    public List<NotificationInfo> getNotificationsByDestUserId(Long id) {
     List<Notification> notifications =  
         this.notificationRepository.findAllByDestUserId(id);

    return notifications
            .stream()
            .map(notification -> 
                modelMapper.map(notification, NotificationInfo.class))
            .collect(Collectors.toList());

    }

}
