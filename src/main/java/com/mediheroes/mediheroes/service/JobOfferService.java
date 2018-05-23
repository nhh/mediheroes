package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.User;
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

    @PreAuthorize("@userPermission.isFreelancer(#user)")
    public Optional<JobOffer> findById(Long id, User user){
        return jobOfferRepository.findById(id);
    }

    @PreAuthorize("@jobOfferPermission.canCreateJobOffer(#jobOffer, #user)")
    public JobOffer create(JobOffer jobOffer, User user){
        return jobOfferRepository.save(jobOffer);
    }

    @PreAuthorize("@jobOfferPermission.canGetJobOffersForCompany(#company, #user)")
    public Iterable<JobOffer> getAllByCompanyId(Company company, User user) {
        return jobOfferRepository.findAllByCompanyId(company.getId());
    }

    @PreAuthorize("@jobOfferPermission.canUpdateJobOffer(#jobOffer, #user)")
    public JobOffer updateJobOffer(JobOffer jobOffer, User user) {
        return jobOfferRepository.save(jobOffer);
    }

    @PreAuthorize("@userPermission.isFreelancer(#user)")
    public Iterable<JobOffer> findAll(User user) {
        return jobOfferRepository.findAll();
    }
}
