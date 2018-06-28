package com.mediheroes.mediheroes.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Address {

    @Column
    @NotNull
    private String street;

    @Column
    @NotNull
    private String state;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private int zip;

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zip == address.zip &&
            Objects.equals(street, address.street) &&
            Objects.equals(state, address.state) &&
            Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {

        return Objects.hash(street, state, city, zip);
    }
}
