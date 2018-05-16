package com.mediheroes.mediheroes.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private boolean active;

    @NotNull
    @Column
    private boolean verified;

    @NotNull
    @Column
    private String firstname;

    @NotNull
    @Column
    private String lastname;

    @Embedded
    private Address address;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    private Company employer;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
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

    public String getFullname(){
        return this.firstname + this.lastname;
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

    public List<String> getRoles(){
        var roles = new ArrayList<String>();

        if(hasCompany()){
            roles.add("OWNER");
        } else if (isEmployee()) {
            roles.add("EMPLOYEE");
        } else {
            roles.add("FREELANCER");
        }

        return roles;
    }

    public boolean isEmployee() {
        return this.employer == null;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public boolean hasCompany() {
        return company != null;
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
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
