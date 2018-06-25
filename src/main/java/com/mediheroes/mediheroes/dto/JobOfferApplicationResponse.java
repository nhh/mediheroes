package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.JobOfferApplication;

public class JobOfferApplicationResponse {

    private final String description;
    private final Long id;
    private final UserResponse user;
    private final JobOfferResponse jobOffer;

    public JobOfferApplicationResponse(JobOfferApplication jobOfferApplication){
        this.description = jobOfferApplication.getDescription();
        this.id = jobOfferApplication.getId();
        this.user = new UserResponse(jobOfferApplication.getUser());
        this.jobOffer = new JobOfferResponse(jobOfferApplication.getJobOffer());
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public UserResponse getUser() {
        return user;
    }

    public JobOfferResponse getJobOffer() {
        return jobOffer;
    }
}
