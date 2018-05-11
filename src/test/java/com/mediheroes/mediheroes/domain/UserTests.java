package com.mediheroes.mediheroes.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTests {

    @Test
    public void setAddress() {

        var user = new User();
        assertNull(user.getAddress());
        var address = new Address();
        user.setAddress(address);
        assertEquals(user.getAddress(), address);

    }

    @Test
    public void setCompany() {
        // Setup
        //fail("Implement me");
        // Precondition
        //fail("Implement me");
        // Test
        //fail("Implement me");
        // Postcondition
        //fail("Implement me");
    }

}
