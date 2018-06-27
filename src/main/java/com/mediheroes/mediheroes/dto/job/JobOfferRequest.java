package com.mediheroes.mediheroes.dto.job;

import javax.validation.constraints.NotNull;

public class JobOfferRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer salary;

    @NotNull
    private String job;

    @NotNull
    private Long companyId;

    @NotNull
    private Long locationId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
