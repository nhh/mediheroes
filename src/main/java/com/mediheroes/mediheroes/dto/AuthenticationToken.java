package com.mediheroes.mediheroes.dto;

public class AuthenticationToken {
    private final String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
