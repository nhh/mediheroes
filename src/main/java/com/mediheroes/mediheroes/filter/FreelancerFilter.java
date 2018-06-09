package com.mediheroes.mediheroes.filter;

import com.mediheroes.mediheroes.domain.User;
import org.springframework.stereotype.Component;

@Component("freelancerFilter")
public class FreelancerFilter {


    public boolean hasValidAddress(User user){
        return user.getAddress() != null;
    }


}
