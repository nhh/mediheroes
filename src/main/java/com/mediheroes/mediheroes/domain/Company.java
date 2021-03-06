package com.mediheroes.mediheroes.domain;

import com.mediheroes.mediheroes.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private boolean active;

    @NotNull
    @Column
    private boolean verified;

    @NotNull
    @Column
    private String email;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobOffer> jobOffers = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Location> locations = new HashSet<>();

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    private Set<User> employees = new HashSet<>();

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobOffers(Set<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    public void addLocation(Location location){
        locations.add(location);
        location.setCompany(this);
    }

    public void removeLocation(Location location){
        locations.remove(location);
        location.setCompany(null);
    }

    public void addEmployee(User employee){
        employees.add(employee);
        employee.setEmployer(this);
    }

    public void removeEmployee(User employee){
        employees.remove(employee);
        employee.removeEmployer();
    }

    public void addJobOffer(JobOffer jobOffer){
        jobOffers.add(jobOffer);
        jobOffer.setCompany(this);
    }

    public void removeJobOffer(JobOffer jobOffer){
        jobOffers.remove(jobOffer);
        jobOffer.setCompany(null);
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
