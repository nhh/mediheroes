package com.mediheroes.mediheroes.dto.user;

import javax.validation.constraints.NotNull;

public class AddressRequest {

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String street;

    @NotNull
    private Integer zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
