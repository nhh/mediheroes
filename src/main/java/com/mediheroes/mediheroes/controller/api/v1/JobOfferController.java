package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.dto.JobOfferRequest;
import com.mediheroes.mediheroes.dto.JobOfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-offers")
@Transactional
public class JobOfferController {

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getOneJobOffer(
        @PathVariable Long id
    ){
        return null;
    }

    @PostMapping("")
    public ResponseEntity<JobOfferResponse> createJobOffer(
        @RequestParam("companyId") Long companyId,
        @RequestParam("locationId") Long locationId,
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ) {
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

}
