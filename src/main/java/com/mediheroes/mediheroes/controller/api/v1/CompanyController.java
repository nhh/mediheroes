package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.dto.CompanyResponse;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private static CompanyService companyService;
    private static LocationService locationService;

    public CompanyController(
        CompanyService companyServiceImpl,
        LocationService locationServiceImpl
    ) {
        companyService = companyServiceImpl;
        locationService = locationServiceImpl;
    }

    @GetMapping("")
    @Transactional
    public ArrayList<CompanyResponse> companies() {
        var companyList = new ArrayList<CompanyResponse>();
        companyService.findAll().forEach(company -> companyList.add(new CompanyResponse(company)));
        return companyList;
    }

    @GetMapping("/{id}")
    @Transactional
    public CompanyResponse getOneCompany(@PathVariable Long id) {
        return companyService.find(id)
                            .map(CompanyResponse::new)
                            .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(name = "", consumes = "application/json")
    @Transactional
    public ResponseEntity<CompanyResponse> createCompany(
        @RequestBody Company companyParams
    ){
        var company = companyService.save(companyParams);
        return new ResponseEntity<>(new CompanyResponse(company), HttpStatus.CREATED);
    }


    @GetMapping("/{id}/locations")
    @Transactional
    public ResponseEntity<List<Location>> getAllLocations(@PathVariable Long id){
        return companyService.find(id)
                            .map(company -> (new ResponseEntity<>(company.getLocations(), HttpStatus.OK)))
                            .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/{id}/locations/{locationId}")
    @Transactional
    public ResponseEntity<Location> getOneLocation(
        @PathVariable Long id,
        @PathVariable int locationId
    ) {
        return new ResponseEntity<>(locationService.find(locationId).orElseThrow(EntityNotFoundException::new), HttpStatus.OK);
    }



}
