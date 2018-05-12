package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.JobOffer;

public class JobOfferResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final Long locationId;
    private final Long companyId;
    private final Integer salary;
    private final String job;

    public JobOfferResponse(JobOffer jobOffer) {
        this.id = jobOffer.getId();
        this.name = jobOffer.getName();
        this.description = jobOffer.getDescription();
        this.locationId = jobOffer.getLocation().getId();
        this.companyId = jobOffer.getCompany().getId();
        this.salary = jobOffer.getSalary();
        this.job = jobOffer.getJob();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }

    public Long getId() {
        return id;
    }
}
