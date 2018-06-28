package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.repository.CompanyRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
    public void addLocationToCompany(Company company, Location location) {
        company.addLocation(location);
        companyRepository.save(company);
    }

    @PreAuthorize("@companyPermission.canAddJobOffer(#company, #user)")
    public void addJobOfferToCompany(JobOffer jobOffer, Company company, User user) {
        company.addJobOffer(jobOffer);
        save(company);
    }

    @PreAuthorize("@companyPermission.canGetJobOffers(#company, #user)")
    public Set<JobOffer> getJobOffers(Company company, User user) {
        return company.getJobOffers();
    }

    @PreAuthorize("@companyPermission.canDeleteJobOffer(#company, #user)")
    public void deleteJobOffer(Company company, JobOffer jobOffer, User user) {
        company.removeJobOffer(jobOffer);
        save(company);
    }

    public long countCompanies() {
        return this.companyRepository.count();
    }
}
