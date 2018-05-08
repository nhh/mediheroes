package com.mediheroes.mediheroes.domain;

import java.io.Serializable;
import java.util.List;

public class UserSession implements Serializable {

    private final boolean active;
    private final boolean verified;
    private final String email;
    private final String password;
    private final List<String> roles;

    public UserSession(User user) {
        active = user.isActive();
        verified = user.isVerified();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
    }

    public boolean isActive() {
        return active;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }
}
