package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.Company;

public class CompanyResponse {

    private final String name;
    private final String email;
    private final boolean verified;
    private final boolean active;
    private final Long id;

    public CompanyResponse(Company company) {
        name = company.getName();
        active = company.isActive();
        verified = company.isVerified();
        email = company.getEmail();
        id = company.getId();
    }

    public boolean isActive() {
        return active;
    }

    public boolean isVerified() {
        return verified;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
