package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.JobOfferApplication;
import com.mediheroes.mediheroes.dto.JobOfferApplicationRequest;
import com.mediheroes.mediheroes.dto.JobOfferApplicationResponse;
import com.mediheroes.mediheroes.dto.JobOfferResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.JobOfferApplicationService;
import com.mediheroes.mediheroes.service.JobOfferService;
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
    private final JobOfferApplicationService jobOfferApplicationService;


    public JobOfferController(
        JobOfferService jobOfferService,
        CompanyService companyService,
        UserService userService,
        JobOfferApplicationService jobOfferApplicationService
    ) {
        this.companyService = companyService;
        this.jobOfferService = jobOfferService;
        this.userService = userService;
        this.jobOfferApplicationService = jobOfferApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getOneJobOffer(
        @PathVariable Long id
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var jobOffer = jobOfferService
            .findById(id, user)
            .map(JobOfferResponse::new)
            .orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<JobOfferResponse[]> getAllJobOffers() {
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var jobOffers = StreamSupport
            .stream(jobOfferService.findAll(user).spliterator(), false)
            .map(JobOfferResponse::new)
            .toArray(JobOfferResponse[]::new);

        return new ResponseEntity<>(jobOffers, HttpStatus.OK);
    }

    @PostMapping("/{id}/application")
    public ResponseEntity<JobOfferApplicationResponse> createApplication(
        @Valid @RequestBody JobOfferApplicationRequest jobOfferApplicationRequest,
        @PathVariable Long id
    ){
        var user = this.userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var jobOffer = this.jobOfferService
            .findById(id, user)
            .orElseThrow(EntityNotFoundException::new);

        var application = new JobOfferApplication();

        application.setDescription(jobOfferApplicationRequest.getDescription());
        application.setJobOffer(jobOffer);
        application.setUser(user);

        application = this.jobOfferApplicationService.createJobOfferApplication(application, user);

        return new ResponseEntity<JobOfferApplicationResponse>(
            new JobOfferApplicationResponse(application), HttpStatus.CREATED
        );
    }

}
