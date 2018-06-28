package com.mediheroes.mediheroes.domain.user;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOfferApplication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public enum Type {
        FREELANCER,
        EMPLOYEE,
        OWNER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @Column
    private boolean active;

    @NotNull
    @Column
    private boolean verified;

    @Embedded
    private Address address;

    @Embedded
    private Profile profile;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<JobOfferApplication> applications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Company employer;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.setType(Type.OWNER);
        company.setOwner(this);
        this.company = company;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Set<String> getRoles(){
        return Set.of(type.toString());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasCompany() {
        return company != null;
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.setType(Type.EMPLOYEE);
        this.employer = employer;
    }

    public Set<JobOfferApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobOfferApplication> applications) {
        this.applications = applications;
    }

    public void removeEmployer(){
        this.setEmployer(null);
        this.setType(Type.FREELANCER);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
