package com.joecis.quick_fix.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findAllByDestUserId(Long destUserId);
}
