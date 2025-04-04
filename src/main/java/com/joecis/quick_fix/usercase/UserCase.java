package com.joecis.quick_fix.usercase;

import java.time.LocalDateTime;
import java.util.Set;

import com.joecis.quick_fix.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

@Entity
public class UserCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime create_date;
    private LocalDateTime modified_date;

    @OneToOne( cascade = CascadeType.ALL)
    private Location location;

    @OneToMany( cascade = CascadeType.ALL)
    private Set<Image> images;

    @ManyToOne
    @JoinColumn(name = "createUserId", nullable = false)
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "solverUserid")
    private User solverUser;

    @Column(nullable = false)
    private UserCaseStatus status;
    

    public UserCase(@Size(max = 40) String title,
                    @Size(max = 255) String description,
                    LocalDateTime create_date,
                    LocalDateTime modified_date,
                    User createUser,
                    User solverUser,
                    UserCaseStatus status) {
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.modified_date = modified_date;
        this.createUser = createUser;
        this.solverUser = solverUser;
        this.status = status;
    }

    public UserCase() {
    }

    /*
     * IMPORTANT JAVA 8 COMPATIBILITY: 
     *            The inclusion of annotation in UserCase constructor wouldn't 
     *            be valid in Java 8 and manual validation would have to be 
     *            done inside the constructor.
     */
    public UserCase(@Size(max = 40) String title,
                    @Size(max = 255) String description, 
                    LocalDateTime create_date,
                    LocalDateTime modified_date,
                    User create_user, 
                    UserCaseStatus status ) {
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.modified_date = modified_date;
        this.createUser = create_user;
        this.status = status;
    }

    public UserCase(Long id,
                    @Size(max = 40) String title, 
                    @Size(max = 255) String description,
                    LocalDateTime create_date,
                    LocalDateTime modified_date,
                    Location location,
                    Set<Image> images,
                    User create_user,
                    User solver_user, 
                    UserCaseStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.modified_date = modified_date;
        this.location = location;
        this.images = images;
        this.createUser = create_user;
        this.solverUser = solver_user;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User create_user) {
        this.createUser = create_user;
    }

    public User getSolver_user() {
        return solverUser;
    }

    public void setSolver_user(User solver_user) {
        this.solverUser = solver_user;
    }

    public UserCaseStatus getStatus() {
        return status;
    }

    public void setStatus(UserCaseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserCase [id=" + id + 
               ", title=" + title +
               ", description=" + description +
               ", create_date=" + create_date +
               ", modified_date=" + modified_date + 
               ", location=" + location.toString() +
               ", images=" + images.toString() +
               ", create_user=" + createUser +
               ", solver_user=" + solverUser +
               ", status=" + status + "]"; }

}
