package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.JobOffer;
import org.springframework.stereotype.Component;

@Component("jobOfferPermission")
public class JobOfferPermission {

    public boolean canCreateJobOffer(JobOffer jobOffer){
        return true;
    }
}
