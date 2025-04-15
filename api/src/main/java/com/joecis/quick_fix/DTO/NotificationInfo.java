package com.joecis.quick_fix.DTO;

import java.time.LocalDateTime;

import com.joecis.quick_fix.notification.NotificationStatus;
import com.joecis.quick_fix.notification.NotificationType;

public class NotificationInfo {
    private Long id;
    private NotificationType type;
    private NotificationStatus status;
    LocalDateTime createDate;
    private Long  sourceUserId;
    private Long  destUserId;
    private Long  usercaseId;

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
    public Long getSourceUserId() {
        return sourceUserId;
    }
    public void setSourceUserId(Long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }
    public Long getDestUserId() {
        return destUserId;
    }
    public void setDestUserId(Long destUserId) {
        this.destUserId = destUserId;
    }
    public Long getUsercaseId() {
        return usercaseId;
    }
    public void setUsercaseId(Long usercaseId) {
        this.usercaseId = usercaseId;
    }
}
