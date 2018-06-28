package com.mediheroes.mediheroes.dto.user;

import com.mediheroes.mediheroes.domain.user.User;

public class UserProfileResponse {

    private final String email;
    private final String firstname;
    private final String lastname;

    public UserProfileResponse(User user) {
        this.email = user.getProfile().getEmail();
        this.firstname = user.getProfile().getFirstname();
        this.lastname = user.getProfile().getLastname();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
