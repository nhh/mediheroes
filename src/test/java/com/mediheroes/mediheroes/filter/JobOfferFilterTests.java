package com.mediheroes.mediheroes.filter;

import com.mediheroes.mediheroes.domain.user.Address;
import com.mediheroes.mediheroes.domain.user.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobOfferFilterTests {

    private final JobOfferFilter jobOfferFilter = new JobOfferFilter();

    /**
     * PLEASE REFER TO OUR OFFICIAL TEST SETUP!
     * SPTP (SETUP,PRECONDITION,TEST,POSTCONDITION)
     * AND
     * FPN (FALSE,POSTIVE,NULL)
    **/

    @Test
    public void hasValidAddress(){
        var user = new User();
        user.setAddress(new Address());
        assertTrue(this.jobOfferFilter.hasValidAddress(user));
    }

    @Test
    public void hasInvalidAddress(){
        var user = new User();
        assertFalse(this.jobOfferFilter.hasValidAddress(user));
    }


}
