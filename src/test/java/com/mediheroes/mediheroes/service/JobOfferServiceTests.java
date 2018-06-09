package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Address;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.permission.JobOfferPermission;
import com.mediheroes.mediheroes.repository.JobOfferRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobOfferServiceTests {

    /*
        Test advice:
        Please use the following test scheme:

        1. Setup

        2. Precondition

        3. Test

        4. Postcondition
    */

    @Mock
    private JobOfferRepository jobOfferRepository;

    private final JobOfferPermission jobOfferPermission = new JobOfferPermission();



    @BeforeAll
    public void mockJobOfferRepositoryMethods() {

        var jobOffer = new JobOffer();
        jobOffer.setName("Hello World");
        jobOffer.setDescription("Hello Mock");
        jobOffer.setJob("JOBJOB");
        jobOffer.setSalary(100);

        when(this.jobOfferRepository.findById(1L)).thenReturn(Optional.of(jobOffer));
    }

    @Test
    public void validFindById() {

        var jobOfferService = new JobOfferService(jobOfferRepository);

        var sender = new User();

        sender.setAddress(new Address());
        sender.setEmail("mock@mocked.com");
        sender.setLastname("mocked");
        sender.setFirstname("mocked");
        sender.setId(1L);
        sender.setType(User.Type.FREELANCER);
        sender.setActive(true);
        sender.setVerified(true);

        var jobOffer = jobOfferService.findById(1L, sender);

        assertTrue(jobOffer.isPresent());
    }

    @Test
    public void invalidFindById() {

        var jobOfferService = new JobOfferService(jobOfferRepository);

        var sender = new User();

        sender.setAddress(new Address());
        sender.setEmail("mock@mocked.com");
        sender.setLastname("mocked");
        sender.setFirstname("mocked");
        sender.setId(1L);
        sender.setType(User.Type.FREELANCER);
        sender.setActive(true);
        sender.setVerified(true);

        var jobOffer = jobOfferService.findById(2L, sender);

        assertFalse(jobOffer.isPresent());
    }

}
