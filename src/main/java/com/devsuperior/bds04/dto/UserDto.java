package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.entities.User;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Email
    private String email;

    private transient Set<RoleDto> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserDto(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        entity.getRoles().forEach(this::accept);
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

    public Set<RoleDto> getRoles() {
        return roles;
    }

    private void accept(Role role) {
        this.roles.add(new RoleDto(role));
    }
}
