package com.joecis.quick_fix.notification;

import java.time.LocalDateTime;

import com.joecis.quick_fix.user.User;
import com.joecis.quick_fix.usercase.UserCase;

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
    private User sourceUser;

    @ManyToOne
    @JoinColumn(name = "dest_user_id", nullable = false)
    private User destUser;

    @ManyToOne
    @JoinColumn(name = "usercase_id", nullable = false)
    private UserCase usercase;

    public Notification(NotificationType type, NotificationStatus status, LocalDateTime createDate, User sourceUser,
            User destUser, UserCase usercase) {
        this.type = type;
        this.status = status;
        this.createDate = createDate;
        this.sourceUser = sourceUser;
        this.destUser = destUser;
        this.usercase = usercase;
    }

    public Notification(Long id, NotificationType type, NotificationStatus status, LocalDateTime createDate,
            User sourceUser, User destUser, UserCase usercase) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.createDate = createDate;
        this.sourceUser = sourceUser;
        this.destUser = destUser;
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

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }

    public User getDestUser() {
        return destUser;
    }

    public void setDestUser(User destUser) {
        this.destUser = destUser;
    }

    public UserCase getUsercase() {
        return usercase;
    }

    public void setUsercase(UserCase usercase) {
        this.usercase = usercase;
    }

}
