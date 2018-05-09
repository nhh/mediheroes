package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.repository.JobOfferRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public Optional<JobOffer> findById(Long id){
        return jobOfferRepository.findById(id);
    }

    @PreAuthorize("@jobOfferPermission.canCreateJobOffer(#jobOffer)")
    public JobOffer create(JobOffer jobOffer){
        return jobOfferRepository.save(jobOffer);
    }
}
