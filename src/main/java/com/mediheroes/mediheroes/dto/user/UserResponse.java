package com.mediheroes.mediheroes.dto.user;

import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.dto.company.CompanyResponse;

import java.io.Serializable;
import java.util.Set;

public class UserResponse {

    private final String lastname;
    private final String firstname;
    private final String email;
    private final CompanyResponse company;
    private final Set<String> roles;
    private final String imageId;

    private final Long id;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstname = user.getProfile().getFirstname();
        this.lastname = user.getProfile().getLastname();
        this.email = user.getProfile().getEmail();
        this.roles = user.getRoles();
        this.imageId = user.getProfile().getImageId();
        if(user.getCompany() == null){
            this.company = null;
        } else {
            this.company = new CompanyResponse(user.getCompany());
        }
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
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

    public CompanyResponse getCompany() {
        return company;
    }

    public String getImageId() {
        return imageId;
    }
}
