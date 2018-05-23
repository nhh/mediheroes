package com.mediheroes.mediheroes.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CompanyTests {

    @Test
    public void removeLocation() {
        var company = new Company();
        var location = new Location();
        company.addLocation(location);

        assertEquals(company.getLocations().size(), 1);

        company.removeLocation(location);

        assertEquals(company.getLocations().size(), 0);
        assertEquals(company.getLocations().contains(location), false);
        assertNull(location.getCompany());
    }

    @Test
    public void addLoaction() {
        var company = new Company();
        var location = new Location();

        assertEquals(company.getLocations().size(), 0);

        company.addLocation(location);

        assertEquals(company.getLocations().size(), 1);
        assertEquals(company.getLocations().contains(location), true);
        assertEquals(location.getCompany(), company);

    }

    @Test
    public void removeJobOffer(){
        var company = new Company();
        var jobOffer = new JobOffer();

        company.addJobOffer(jobOffer);

        assertEquals(company.getJobOffers().size(), 1);

        company.removeJobOffer(jobOffer);

        assertEquals(company.getJobOffers().size(), 0);
        assertEquals(company.getJobOffers().contains(jobOffer), false);
        assertNull(jobOffer.getCompany());

    }

    @Test
    public void addJobOffer(){
        var company = new Company();
        var jobOffer = new JobOffer();

        assertEquals(company.getJobOffers().size(), 0);

        company.addJobOffer(jobOffer);

        assertEquals(company.getJobOffers().size(), 1);
        assertEquals(company.getJobOffers().contains(jobOffer), true);
        assertEquals(jobOffer.getCompany(), company);
    }

    @Test
    public void addEmployee() {
        var employer  = new Company();
        var user = new User();

        assertNull(user.getEmployer());
        assertNull(user.getType());

        employer.addEmployee(user);

        assertEquals(user.getType(), User.Type.EMPLOYEE);
        assertEquals(employer.getEmployees().contains(user), true);
    }

    @Test
    public void removeEmployee() {
        var employer  = new Company();
        var user = new User();

        employer.addEmployee(user);

        assertEquals(employer.getEmployees().size(), 1);

        employer.removeEmployee(user);

        assertEquals(employer.getEmployees().size(), 0);
        assertEquals(user.getType(), User.Type.FREELANCER);
        assertEquals(employer.getEmployees().contains(user), false);
    }

}
