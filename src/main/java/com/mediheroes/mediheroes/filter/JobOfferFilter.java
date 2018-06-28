package com.mediheroes.mediheroes.filter;

import com.mediheroes.mediheroes.domain.user.User;
import org.springframework.stereotype.Component;

@Component("jobOfferFilter")
public class JobOfferFilter {


    public boolean hasValidAddress(User user){
        return user.getAddress() != null;
    }


}
