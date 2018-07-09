package com.mediheroes.mediheroes.domain.user;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Profile {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String firstname;

    @NotNull
    @Column
    private String lastname;

    @Column
    private String imageId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getFullname(){
        return this.firstname + " " + this.lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String pictureId) {
        this.imageId = pictureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(email, profile.email) &&
            Objects.equals(password, profile.password) &&
            Objects.equals(firstname, profile.firstname) &&
            Objects.equals(lastname, profile.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password, firstname, lastname);
    }
}
