package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.Address;
import com.mediheroes.mediheroes.domain.Location;

public class LocationResponse {

    private final String email;
    private final Address address;
    private final Long id;

    private final String name;

    public LocationResponse(Location location) {
        this.name = location.getName();
        this.email = location.getEmail();
        this.address = location.getAddress();
        this.id = location.getId();
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }


}
