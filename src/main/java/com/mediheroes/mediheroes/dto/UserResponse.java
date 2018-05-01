package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.User;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private static String lastname;
    private static String firstname;
    private static String email;

    public UserResponse(User user) {
        firstname = user.getFirstname();
        lastname = user.getLastname();
        email = user.getEmail();
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserResponse.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
