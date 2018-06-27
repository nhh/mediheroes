package com.mediheroes.mediheroes.dto.user;

import com.mediheroes.mediheroes.domain.Address;

public class AddressResponse {

    private final String street;
    private final String city;
    private final Integer zip;
    private final String state;

    public AddressResponse(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.zip = address.getZip();
        this.state = address.getState();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }
}
