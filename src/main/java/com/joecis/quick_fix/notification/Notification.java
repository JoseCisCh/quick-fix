package com.joecis.quick_fix.notification;

import java.time.LocalDateTime;

import com.joecis.quick_fix.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
   
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(nullable = false)
    private NotificationType type;
    @Column(nullable = false)
    private NotificationStatus status;
    LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "source_user_id", nullable = false)
    private User source_user;

    @ManyToOne
    @JoinColumn(name = "dest_user_id", nullable = false)
    private User dest_user;

    @ManyToOne
    @JoinColumn(name = "usercase_id", nullable = false)
    private User usercase;

    public Notification(Long id, NotificationType type, NotificationStatus status, LocalDateTime createDate,
            User source_user, User dest_user, User usercase) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.createDate = createDate;
        this.source_user = source_user;
        this.dest_user = dest_user;
        this.usercase = usercase;
    }

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public User getSource_user() {
        return source_user;
    }

    public void setSource_user(User source_user) {
        this.source_user = source_user;
    }

    public User getDest_user() {
        return dest_user;
    }

    public void setDest_user(User dest_user) {
        this.dest_user = dest_user;
    }

    public User getUsercase() {
        return usercase;
    }

    public void setUsercase(User usercase) {
        this.usercase = usercase;
    }

}
