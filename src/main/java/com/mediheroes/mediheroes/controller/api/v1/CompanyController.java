package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.dto.*;
import com.mediheroes.mediheroes.exception.EntityAlreadyExistsException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.LocationService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@Transactional
public class CompanyController {

    private final CompanyService companyService;
    private final LocationService locationService;
    private final UserService userService;

    public CompanyController (
        CompanyService companyService,
        LocationService locationService,
        UserService userService
    ) {
        this.companyService = companyService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("")
    public ArrayList<CompanyResponse> companies() {
        var companyList = new ArrayList<CompanyResponse>();
        companyService.findAll().forEach(company -> companyList.add(new CompanyResponse(company)));
        return companyList;
    }

    @GetMapping("/{id}")
    public CompanyResponse getOneCompany(@PathVariable Long id) {
        return companyService.find(id)
                            .map(CompanyResponse::new)
                            .orElseThrow(EntityNotFoundException::new);
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
        var user = userService.find(companyParams.getOwnerId()).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new CompanyResponse(userService.createCompany(user, company)), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<ArrayList<UserResponse>> addEmployeeToCompany(
        @PathVariable Long id,
        @RequestBody String email
    ) {
        var company = companyService.find(id).orElseThrow(EntityNotFoundException::new);
        var employee = userService.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        company.addEmployee(employee);
        companyService.save(company);
        // Todo refactor me.... .toArray
        var userList = new ArrayList<UserResponse>();
        company.getEmployees().stream().map(UserResponse::new).forEach(userList::add);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<ArrayList<UserResponse>> getEmployeesOfCompany (
        @PathVariable Long id
    ) {
        var company = companyService.find(id).orElseThrow(EntityNotFoundException::new);
        var userList = new ArrayList<UserResponse>();
        company.getEmployees().stream().map(UserResponse::new).forEach(userList::add);
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

}
