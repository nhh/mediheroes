package com.mediheroes.mediheroes.dto.company;

import com.mediheroes.mediheroes.domain.user.Address;

import javax.validation.constraints.NotNull;

public class LocationRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
