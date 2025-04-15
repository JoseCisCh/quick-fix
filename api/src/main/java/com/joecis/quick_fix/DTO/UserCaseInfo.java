package com.joecis.quick_fix.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.joecis.quick_fix.usercase.Image;
import com.joecis.quick_fix.usercase.Location;
import com.joecis.quick_fix.usercase.UserCaseStatus;

public class UserCaseInfo {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime create_date;
    private LocalDateTime modified_date;
    private Location location;
    private Set<Image> images;
    private UserCaseStatus status;
    private Long createUserId;
    private Long solverUserId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreate_date() {
        return create_date;
    }
    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }
    public LocalDateTime getModified_date() {
        return modified_date;
    }
    public void setModified_date(LocalDateTime modified_date) {
        this.modified_date = modified_date;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Set<Image> getImages() {
        return images;
    }
    public void setImages(Set<Image> images) {
        this.images = images;
    }
    public UserCaseStatus getStatus() {
        return status;
    }
    public void setStatus(UserCaseStatus status) {
        this.status = status;
    }

    public Long getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getSolverUserId() {
        return solverUserId;
    }
    public void setSolverUserId(Long solverUserId) {
        this.solverUserId = solverUserId;
    }
}
