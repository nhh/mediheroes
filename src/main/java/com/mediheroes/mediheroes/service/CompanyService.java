package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.repository.CompanyRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final LocationService locationService;

    public CompanyService(
        CompanyRepository companyRepository,
        LocationService locationService
    ) {
        this.companyRepository = companyRepository;
        this.locationService = locationService;
    }

    public Optional<Company> find(long id) {
        return companyRepository.findById(id);
    }

    public Iterable<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @PreAuthorize("@companyPermission.canAddLocation(#company, #location)")
    public Location addLocationToCompany(Company company, Location location) {
        location.setCompany(company);
        locationService.save(location);
        return location;
    }
}
