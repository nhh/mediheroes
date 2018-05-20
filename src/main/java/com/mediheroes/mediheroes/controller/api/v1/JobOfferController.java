package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.dto.JobOfferRequest;
import com.mediheroes.mediheroes.dto.JobOfferResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.JobOfferService;
import com.mediheroes.mediheroes.service.LocationService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/job-offers")
@Transactional
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final CompanyService companyService;
    private final LocationService locationService;
    private final UserService userService;


    public JobOfferController(
        JobOfferService jobOfferService,
        CompanyService companyService,
        LocationService locationService,
        UserService userService
    ) {
        this.companyService = companyService;
        this.jobOfferService = jobOfferService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getOneJobOffer(
        @PathVariable Long id
    ){
        var jobOffer = jobOfferService
            .findById(id)
            .map(JobOfferResponse::new)
            .orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<JobOfferResponse> createJobOffer (
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ) {
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);
        var company = companyService
            .find(jobOfferRequest.getCompanyId())
            .orElseThrow(EntityNotFoundException::new);
        var location = locationService
            .find(jobOfferRequest.getLocationId())
            .orElseThrow(EntityNotFoundException::new);

        var jobOffer = new JobOffer();
        jobOffer.setCompany(company);
        jobOffer.setLocation(location);
        jobOffer.setName(jobOfferRequest.getName());
        jobOffer.setDescription(jobOfferRequest.getDescription());
        jobOffer.setJob(jobOfferRequest.getJob());
        jobOffer.setSalary(jobOfferRequest.getSalary());

        jobOfferService.create(jobOffer, user);

        return new ResponseEntity<>(new JobOfferResponse(jobOffer), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<JobOfferResponse[]> getAllJobOffers(
        @RequestParam("companyId") Long companyId
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);
        var company = companyService
            .find(companyId)
            .orElseThrow(EntityNotFoundException::new);

        var jobOffers = StreamSupport
            .stream(jobOfferService.getAllByCompanyId(company, user).spliterator(), false)
            .map(JobOfferResponse::new)
            .toArray(JobOfferResponse[]::new);

        return new ResponseEntity<>(jobOffers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOfferResponse> updateOneJobOffer(
        @PathVariable Long id,
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);
        var jobOffer = jobOfferService
            .findById(id)
            .orElseThrow(EntityNotFoundException::new);
        var location = locationService
            .find(jobOfferRequest
            .getLocationId()).orElseThrow(EntityNotFoundException::new);

        jobOffer.setSalary(jobOfferRequest.getSalary());
        jobOffer.setJob(jobOfferRequest.getJob());
        jobOffer.setDescription(jobOfferRequest.getDescription());
        jobOffer.setName(jobOfferRequest.getName());
        jobOffer.setLocation(location);

        jobOfferService.updateJobOffer(jobOffer, user);

        return new ResponseEntity<>(new JobOfferResponse(jobOffer), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JobOfferResponse> deleteOneJobOffer(
        @PathVariable Long id
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);
        var jobOffer = jobOfferService
            .findById(id)
            .orElseThrow(EntityNotFoundException::new);
        jobOfferService.deleteJobOffer(jobOffer, user);
        return new ResponseEntity<>(new JobOfferResponse(jobOffer), HttpStatus.OK);
    }

}
