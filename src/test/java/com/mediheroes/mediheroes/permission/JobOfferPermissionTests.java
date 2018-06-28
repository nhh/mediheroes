package com.mediheroes.mediheroes.permission;


import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.user.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobOfferPermissionTests {

    private final JobOfferPermission jobOfferPermission = new JobOfferPermission();

    @Test
    public void cannotBecauseIsFreelancer(){

        var user = new User();
        user.setType(User.Type.FREELANCER);
        var jobOffer = new JobOffer();

        var company = new Company();
        company.setId(1L);

        jobOffer.setCompany(company);

        assertFalse(this.jobOfferPermission.canCreateJobOffer(jobOffer, user));

    }

    @Test
    public void cannotBecauseWrongCompany() {

        var user = new User();
        var jobOffer = new JobOffer();

        var company = new Company();
        company.setId(1L);

        user.setCompany(new Company());
        jobOffer.setCompany(company);

        assertFalse(this.jobOfferPermission.canCreateJobOffer(jobOffer, user));

    }

    @Test
    public void canCreateJobOffer(){

        var user = new User();
        var jobOffer = new JobOffer();

        var company = new Company();
        company.setId(1L);

        user.setCompany(company);
        jobOffer.setCompany(company);

        assertTrue(this.jobOfferPermission.canCreateJobOffer(jobOffer, user));

    }


}
