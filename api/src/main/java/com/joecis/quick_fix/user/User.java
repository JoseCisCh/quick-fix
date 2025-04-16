package com.joecis.quick_fix.user;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joecis.quick_fix.notification.Notification;
import com.joecis.quick_fix.rating.Rating;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.usercase.UserCase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AppUser")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Column(nullable = false, length = 320, unique = true)
    String username;
    @Column(nullable = false, length = 320, unique = true)
    String email;
    @Column(nullable = false, length = 40)
    String firstName;
    @Column(nullable = false, length = 50)
    String lastName;
    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false)
    boolean accountNonExpired;
    @Column(nullable = false)
    boolean accountNonLocked;
    @Column(nullable = false)
    boolean enabled;
    @Column(nullable = false)
    boolean credentialsNonExpired;

    @ManyToMany(cascade = CascadeType.ALL)
    Set<Role> authorities;


    @OneToMany(fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               mappedBy = "user")
    private Set<Rating> ratings;

    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "createUser")
    private Set<UserCase> usercases;

    @OneToMany(fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               mappedBy = "destUser")
    private Set<Notification> notifications;


    public User(String username, String email,
                String firstName, String lastName,
                String password, boolean accountNonExpired,
                boolean accountNonLocked, boolean enabled,
                boolean credentialsNonExpired) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public User(Long id, String username,
            String email, String firstName,
            String lastName, String password,
            boolean accountNonExpired, boolean accountNonLocked,
            boolean enabled, boolean credentialsNonExpired,
            Set<Rating> ratings, Set<UserCase> usercases, 
            Set<Notification> notifications) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.ratings = ratings;
        this.usercases = usercases;
        this.notifications = notifications;
    }

    public User() {
    }

    public User(String email, String firstName, 
            String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String email, String username,  String firstName, 
            String lastName, String password) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(Long id, String email, 
                String firstName, String lastName, 
                String password, Set<Rating> ratings,
                Set<UserCase> usercases) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.ratings = ratings;
        this.usercases = usercases;
    }

    public User(Long id, String email,
                String firstName, String lastName,
                String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<UserCase> getCases() {
        return usercases;
    }

    public void setCases(Set<UserCase> cases) {
        this.usercases = cases;
    }


    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
    /*@Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", ratings=" + (getRatings() != null ? getRatings().toString()
                        : "[]") + ", usercases=" + (getCases() != null ? getCases().toString() : "[]") + "]";
    }*/
    
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Set<UserCase> getUsercases() {
        return usercases;
    }

    public void setUsercases(Set<UserCase> usercases) {
        this.usercases = usercases;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id +
                ", email=" + email +
                ", firstName=" + firstName +
                ", lastName=" + lastName + 
                ", password=" + password + "]";
    }
} 
