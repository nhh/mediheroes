package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.dto.*;
import com.mediheroes.mediheroes.exception.EntityAlreadyExistsException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.JobOfferService;
import com.mediheroes.mediheroes.service.LocationService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/companies")
@Transactional
public class CompanyController {

    private final CompanyService companyService;
    private final LocationService locationService;
    private final UserService userService;
    private final JobOfferService jobOfferService;

    public CompanyController (
        CompanyService companyService,
        LocationService locationService,
        UserService userService,
        JobOfferService jobOfferService
    ) {
        this.companyService = companyService;
        this.locationService = locationService;
        this.userService = userService;
        this.jobOfferService = jobOfferService;
    }

    @GetMapping("")
    public ResponseEntity<CompanyResponse[]> companies() {
        var companyList = StreamSupport
            .stream(companyService.findAll().spliterator(), false)
            .map(CompanyResponse::new)
            .toArray(CompanyResponse[]::new);

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getOneCompany(@PathVariable Long id) {
        var company = companyService.find(id)
                            .map(CompanyResponse::new)
                            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CompanyResponse> createCompany (
        @Valid @RequestBody CompanyRequest companyParams
    ){
        var company = new Company();
        company.setVerified(true);
        company.setActive(true);
        company.setEmail(companyParams.getEmail());
        company.setName(companyParams.getName());

        var user = userService
            .find(companyParams.getOwnerId())
            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(new CompanyResponse(userService.createCompany(user, company)), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity addEmployeeToCompany(
        @PathVariable Long id,
        @RequestBody String email
    ) {
        var company = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var employee = userService
            .findByEmail(email)
            .orElseThrow(EntityNotFoundException::new);

        company.addEmployee(employee);
        companyService.save(company);

        var employeeList = company.getEmployees()
            .stream()
            .map(UserResponse::new)
            .toArray(UserResponse[]::new);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployeesOfCompany (
        @PathVariable Long id
    ) {
        var company = companyService.find(id).orElseThrow(EntityNotFoundException::new);
        var userList = company.getEmployees()
            .stream()
            .map(UserResponse::new)
            .toArray(UserResponse[]::new);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<UserResponse> removeEmployeeFromCompany (
        @PathVariable Long id,
        @PathVariable Long employeeId
    ) {
        var company = companyService.find(id).orElseThrow(EntityNotFoundException::new);
        var employee = userService.find(employeeId).orElseThrow(EntityNotFoundException::new);
        company.removeEmployee(employee);
        companyService.save(company);
        return new ResponseEntity<>(new UserResponse(employee), HttpStatus.OK);
    }

    @GetMapping("/{id}/locations/{locationId}")
    public ResponseEntity<LocationResponse> getOneLocation(
        @PathVariable Long id,
        @PathVariable Long locationId
    ) {
        var responseLocation = companyService.find(id)
            .orElseThrow(EntityNotFoundException::new)
            .getLocations()
            .stream()
            .filter(location -> location.getId().equals(locationId))
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(new LocationResponse(responseLocation), HttpStatus.OK);
    }

    @GetMapping("/{id}/locations")
    public ResponseEntity locations(
        @PathVariable("id") Long id
    ) {
        var locations = companyService.find(id)
                                        .orElseThrow(EntityNotFoundException::new)
                                        .getLocations()
                                        .stream()
                                        .map(LocationResponse::new)
                                        .toArray(LocationResponse[]::new);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/locations/{locationId}")
    public ResponseEntity<LocationResponse> deleteOneLocation(
        @PathVariable Long id,
        @PathVariable Long locationId
    ) {
        var company = companyService.find(id).orElseThrow(EntityAlreadyExistsException::new);
        var location = locationService.find(locationId).orElseThrow(EntityAlreadyExistsException::new);
        company.getLocations().remove(location);
        companyService.save(company);
        return new ResponseEntity<>(new LocationResponse(location), HttpStatus.OK);
    }

    @PostMapping("/{id}/locations")
    public ResponseEntity createLocation(
        @PathVariable Long id,
        @Valid @RequestBody LocationRequest locationRequest
    ) {
        var company = companyService.find(id).orElseThrow(EntityNotFoundException::new);

        var location = new Location();

        location.setAddress(locationRequest.getAddress());
        location.setEmail(locationRequest.getEmail());
        location.setName(locationRequest.getName());

        companyService.addLocationToCompany(company, location);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/job-offers/{jobOfferId}")
    public ResponseEntity<JobOfferResponse> updateOneJobOffer(
        @PathVariable Long id,
        @PathVariable Long jobOfferId,
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var jobOffer = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new)
            .getJobOffers().stream()
            .filter(offer -> offer.getId().equals(jobOfferId))
            .findFirst()
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

    @DeleteMapping("/{id}/job-offers/{jobOfferId}")
    public ResponseEntity deleteOneJobOffer(
        @PathVariable Long id,
        @PathVariable Long jobOfferId
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var jobOffer = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new)
            .getJobOffers().stream()
            .filter(offer -> offer.getId().equals(jobOfferId))
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);

        var company = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        companyService.deleteJobOffer(company, jobOffer, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/job-offers")
    public ResponseEntity<JobOfferResponse> createJobOffer (
        @PathVariable Long id,
        @Valid @RequestBody JobOfferRequest jobOfferRequest
    ) {
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var company = companyService
            .find(id)
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

        companyService.addJobOfferToCompany(jobOffer, company, user);

        return new ResponseEntity<>(new JobOfferResponse(jobOffer), HttpStatus.CREATED);
    }


    @GetMapping("/{id}/job-offers/{jobOfferId}")
    public ResponseEntity<JobOfferResponse> getOneJobOffer(
        @PathVariable Long id,
        @PathVariable Long jobOfferId
    ){
        var jobOffer = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new)
            .getJobOffers().stream()
            .filter(offer -> offer.getId().equals(jobOfferId))
            .findFirst()
            .map(JobOfferResponse::new)
            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }

    @GetMapping("/{id}/job-offers")
    public ResponseEntity<JobOfferResponse[]> getAllJobOffersOfCompany(
        @PathVariable Long id
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var company = companyService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var jobOffers = companyService.getJobOffers(company, user)
            .stream()
            .map(JobOfferResponse::new)
            .toArray(JobOfferResponse[]::new);

        return new ResponseEntity<>(jobOffers, HttpStatus.OK);
    }
}
