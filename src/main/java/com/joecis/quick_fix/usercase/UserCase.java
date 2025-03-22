package com.joecis.quick_fix.usercase;

import java.time.LocalDateTime;
import java.util.Set;

import com.joecis.quick_fix.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

public class UserCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 40)
    @Column(nullable = false)
    private String title;

    @Size(max = 255)
    @Column(nullable = false)
    private String description;

    private LocalDateTime create_date;
    private LocalDateTime modified_date;
    private Location location;

    @OneToMany( mappedBy = "user_case")
    private Set<Image> images;

    @ManyToOne
    @JoinColumn(name = "create_id")
    private User create_user;

    @ManyToOne
    @JoinColumn(name = "solver_id")
    private User solver_user;

    private UserCaseStatus status;
    
    public UserCase() {
    }

    /*
     * IMPORTANT JAVA 8 COMPATIBILITY: 
     *            The inclusion of annotation in UserCase constructor wouldn't 
     *            be valid in Java 8 and manual validation would have to be done
     *            inside the constructor
     */
    public UserCase(Long id, @Size(max = 40) String title, @Size(max = 255) String description,
            LocalDateTime create_date, LocalDateTime modified_date, Location location, Set<Image> images,
            User create_user, User solver_user, UserCaseStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.modified_date = modified_date;
        this.location = location;
        this.images = images;
        this.create_user = create_user;
        this.solver_user = solver_user;
        this.status = status;
    }

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

    public User getCreate_user() {
        return create_user;
    }

    public void setCreate_user(User create_user) {
        this.create_user = create_user;
    }

    public User getSolver_user() {
        return solver_user;
    }

    public void setSolver_user(User solver_user) {
        this.solver_user = solver_user;
    }

    public UserCaseStatus getStatus() {
        return status;
    }

    public void setStatus(UserCaseStatus status) {
        this.status = status;
    }

}
