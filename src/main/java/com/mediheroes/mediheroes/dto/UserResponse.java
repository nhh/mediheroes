package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.User;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private final String lastname;
    private final String firstname;
    private final String email;
    private final CompanyResponse company;


    private final Long id;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.company = new CompanyResponse(user.getCompany());
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
}
