package com.mediheroes.mediheroes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "job_offers")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column
    private Integer salary;

    @NotNull
    @Column
    private String job;

    @OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
    private Set<JobOfferApplication> applications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }

    public Company getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<JobOfferApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobOfferApplication> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOffer jobOffer = (JobOffer) o;
        return Objects.equals(id, jobOffer.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
