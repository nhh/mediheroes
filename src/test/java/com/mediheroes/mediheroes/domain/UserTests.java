package com.mediheroes.mediheroes.domain;

import com.mediheroes.mediheroes.domain.user.Address;
import com.mediheroes.mediheroes.domain.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class UserTests {

    @Test
    public void setAddress() {

        var user = new User();
        var address = new Address();
        assertNull(user.getAddress());
        user.setAddress(address);

    }

    @Test
    public void setCompany() {
        var company  = new Company();
        var user = new User();

        assertNull(user.getCompany());
        assertNull(user.getType());

        user.setCompany(company);
        assertEquals(user.getType(), User.Type.OWNER);
        assertEquals(company.getOwner(), user);
    }

    @Test
    public void equals() {
        var user = new User();
        var user1 = new User();

        assertEquals(user, user1);

        user.setId(1L);
        user1.setId(10L);

        assertNotEquals(user, user1);

    }

}
