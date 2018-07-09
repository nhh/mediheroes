package com.mediheroes.mediheroes.domain;

import com.mediheroes.mediheroes.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "job_offer_applications")
public class JobOfferApplication {

    public enum Rating {
        BAD,
        OK,
        GOOD,
        EXCELLENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private JobOffer jobOffer;

    @Enumerated(EnumType.STRING)
    private Rating userRating;

    @Enumerated(EnumType.STRING)
    private Rating companyRating;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
