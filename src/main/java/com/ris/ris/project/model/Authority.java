package com.ris.ris.project.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityID;

    private String authority = "STANDARD_USER";

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getAuthorityID() {
        return authorityID;
    }

    public void setAuthorityID(Long authorityID) {
        this.authorityID = authorityID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}