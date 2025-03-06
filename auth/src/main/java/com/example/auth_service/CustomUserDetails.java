package com.example.auth_service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;


@Getter
public class CustomUserDetails extends User {

    private final String userId;

    public CustomUserDetails(String username, String password, String userId,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
