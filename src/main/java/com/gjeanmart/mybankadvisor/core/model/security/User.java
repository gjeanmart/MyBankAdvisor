package com.gjeanmart.mybankadvisor.core.model.security;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
@Proxy(lazy=false)
@Table(name = "USER")
public class User extends BaseModel implements Serializable {

    private static final long serialVersionUID = 5025690092611203386L;

    @Id
    @Column(name="ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="USERNAME", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name="EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name="PASSWORD", nullable = true, length = 20)
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
    }

}

