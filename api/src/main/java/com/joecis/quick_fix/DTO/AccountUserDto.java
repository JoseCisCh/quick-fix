package com.joecis.quick_fix.DTO;

import java.util.Set;

import com.joecis.quick_fix.role.Role;

public class AccountUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String token;
    private Set<Role> authorities;

    public AccountUserDto() {
    }
    public AccountUserDto(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Set<Role> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
