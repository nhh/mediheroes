package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.User;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private final String lastname;
    private final String firstname;
    private final String email;

    public UserResponse(User user) {
        firstname = user.getFirstname();
        lastname = user.getLastname();
        email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

}
