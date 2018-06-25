package com.mediheroes.mediheroes.repository;

import com.mediheroes.mediheroes.domain.JobOfferApplication;
import org.springframework.data.repository.CrudRepository;

public interface JobOfferApplicationRepository extends CrudRepository<JobOfferApplication, Long> {
    public Iterable<JobOfferApplication> findAllByJobOfferId(Long id);
}
