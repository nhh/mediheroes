package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.JobOfferApplication;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.repository.JobOfferApplicationRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class JobOfferApplicationService {

    private final JobOfferApplicationRepository repository;

    public JobOfferApplicationService(JobOfferApplicationRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize(
        "@jobOfferApplicationPermission.canCreateJobOfferApplication(#application, #user)"
    )
    public JobOfferApplication createJobOfferApplication(JobOfferApplication application, User user) {
        return this.repository.save(application);
    }

}
