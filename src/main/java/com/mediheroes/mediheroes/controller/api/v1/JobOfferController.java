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
    private final UserService userService;


    public JobOfferController(
        JobOfferService jobOfferService,
        CompanyService companyService,
        UserService userService
    ) {
        this.companyService = companyService;
        this.jobOfferService = jobOfferService;
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
            .stream(jobOfferService.findAll().spliterator(), false)
            .map(JobOfferResponse::new)
            .toArray(JobOfferResponse[]::new);

        return new ResponseEntity<>(jobOffers, HttpStatus.OK);
    }

}
