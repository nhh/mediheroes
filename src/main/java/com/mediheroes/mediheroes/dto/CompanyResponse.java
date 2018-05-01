package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.Company;

public class CompanyResponse {

    private final String name;
    private final String email;
    private final boolean verified;
    private final boolean active;

    public CompanyResponse(Company company) {
        name = company.getName();
        active = company.isActive();
        verified = company.isVerified();
        email = company.getEmail();
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
}
