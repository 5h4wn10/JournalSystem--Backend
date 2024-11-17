package com.journalsystem.dto;

import com.journalsystem.model.Role;
import com.journalsystem.model.User;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String fullName, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.roles = user.getRoles();
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
