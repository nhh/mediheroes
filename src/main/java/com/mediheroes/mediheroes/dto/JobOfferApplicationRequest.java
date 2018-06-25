package com.mediheroes.mediheroes.dto;

import javax.validation.constraints.NotNull;

public class JobOfferApplicationRequest {

    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
