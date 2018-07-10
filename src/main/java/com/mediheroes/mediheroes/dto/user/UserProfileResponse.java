package com.mediheroes.mediheroes.dto.user;

import com.mediheroes.mediheroes.domain.user.Profile;

public class UserProfileResponse {

    private final String email;
    private final String firstname;
    private final String lastname;
    private final String imageId;

    public UserProfileResponse(Profile profile) {
        this.email = profile.getEmail();
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.imageId = profile.getImageId();
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

    public String getImageId() {
        return imageId;
    }
}
