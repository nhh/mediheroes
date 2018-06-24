package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.SummaryMetricResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MetricService {

    private final UserService userService;
    private final JobOfferService jobOfferService;
    private final CompanyService companyService;

    public MetricService(
        UserService userService,
        JobOfferService jobOfferService,
        CompanyService companyService
    ) {
        this.userService = userService;
        this.jobOfferService = jobOfferService;
        this.companyService = companyService;
    }

    @PreAuthorize("@userPermission.isAdmin(#sender)")
    public SummaryMetricResponse getActualSummaryMetric(User sender){

        var metrics = new SummaryMetricResponse();

        metrics.setCompanyCount(companyService.countCompanies());
        metrics.setJobOfferCount(jobOfferService.countJobOffers());
        metrics.setUserCount(userService.countUsers());

        return metrics;

    }
}
