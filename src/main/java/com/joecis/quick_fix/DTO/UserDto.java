package com.joecis.quick_fix.DTO;

import java.util.Set;


public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<RatingInfo> ratings;
    private Set<UserCaseInfo> cases;
    private Set<NotificationInfo> notifications;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Set<RatingInfo> getRatings() {
        return ratings;
    }
    public void setRatings(Set<RatingInfo> ratings) {
        this.ratings = ratings;
    }
    public Set<UserCaseInfo> getCases() {
        return cases;
    }
    public void setCases(Set<UserCaseInfo> cases) {
        this.cases = cases;
    }
    public Set<NotificationInfo> getNotifications() {
        return notifications;
    }
    public void setNotifications(Set<NotificationInfo> notifications) {
        this.notifications = notifications;
    }
}
