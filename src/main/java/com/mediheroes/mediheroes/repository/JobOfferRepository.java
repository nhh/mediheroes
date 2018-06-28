package com.mediheroes.mediheroes.repository;

import com.mediheroes.mediheroes.domain.JobOffer;
import org.springframework.data.repository.CrudRepository;

public interface JobOfferRepository extends CrudRepository<JobOffer, Long> {
    public Iterable<JobOffer> findAllByCompanyId(Long id);
}
