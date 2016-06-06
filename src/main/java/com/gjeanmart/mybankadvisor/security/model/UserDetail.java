package com.gjeanmart.mybankadvisor.security.model;

import java.util.Set;

public class UserDetail {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;

    public UserDetail(String id, String firstName, String lastName, String email, Set<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getId() {
        return id;
    }
}
