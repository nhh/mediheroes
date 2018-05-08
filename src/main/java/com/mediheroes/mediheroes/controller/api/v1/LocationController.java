package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.dto.LocationRequest;
import com.mediheroes.mediheroes.dto.LocationResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@Transactional
public class LocationController {

    private final LocationService locationService;
    private final CompanyService companyService;

    public LocationController(
        LocationService locationService,
        CompanyService companyService
    ) {
        this.locationService = locationService;
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getOneLocation(
        @PathVariable Long id,
        @RequestParam("companyId") Long companyId
    ) {
        return locationService.find(id)
            .map((location -> new ResponseEntity<>(new LocationResponse(location), HttpStatus.OK)))
            .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<LocationResponse>> getAllLocations (
        @RequestParam("companyId") Long companyId
    ) {
        var locationList = new ArrayList<LocationResponse>();
        locationService.findAllByCompanyId(companyId).forEach(company -> locationList.add(new LocationResponse(company)));
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationResponse> updateOneLocation(
        @PathVariable Long id,
        @Valid @RequestBody LocationRequest locationRequest
    ){
        return null;
    }

    @PostMapping("")
    public ResponseEntity<LocationResponse> createLocation(
        @Valid @RequestBody LocationRequest locationRequest
    ) {
        var location = new Location();
        location.setAddress(locationRequest.getAddress());
        location.setEmail(locationRequest.getEmail());
        location.setName(locationRequest.getName());
        var company = companyService.find(locationRequest.getCompanyId()).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new LocationResponse(companyService.addLocationToCompany(company, location)), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<LocationResponse> deleteOneLocation(
        @PathVariable Long id
    ) {
        return null;
    }
}
