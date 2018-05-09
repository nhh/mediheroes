package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.dto.JobOfferRequest;
import com.mediheroes.mediheroes.dto.JobOfferResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.JobOfferService;
import com.mediheroes.mediheroes.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-offers")
@Transactional
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final CompanyService companyService;
    private final LocationService locationService;

    public JobOfferController(
        JobOfferService jobOfferService,
        CompanyService companyService,
        LocationService locationService
    ) {
        this.companyService = companyService;
        this.jobOfferService = jobOfferService;
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getOneJobOffer(
        @PathVariable Long id
    ){
        return null;
    }

    @PostMapping("")
    public ResponseEntity<JobOfferResponse> createJobOffer (
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ) {
        var jobOffer = new JobOffer();
        var company = companyService.find(jobOfferRequest.getCompanyId()).orElseThrow(EntityNotFoundException::new);
        var location = locationService.find(jobOfferRequest.getLocationId()).orElseThrow(EntityNotFoundException::new);

        jobOffer.setCompany(company);
        jobOffer.setLocation(location);
        jobOffer.setName(jobOfferRequest.getName());
        jobOffer.setDescription(jobOfferRequest.getDescription());
        jobOffer.setJob(jobOfferRequest.getJob());
        jobOffer.setSalary(jobOfferRequest.getSalary());

        jobOfferService.create(jobOffer);

        return null;
    }

    @GetMapping("")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers(){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOfferResponse> updateOneJobOffer(
        @PathVariable Long id,
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JobOfferResponse> deleteOneJobOffer(
        @PathVariable Long id
    ){
        return null;
    }

}
