package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.JobOfferApplication;
import com.mediheroes.mediheroes.domain.User;
import org.springframework.stereotype.Component;

@Component("jobOfferApplicationPermission")
public class JobOfferApplicationPermission {

    private final UserPermission userPermission;

    public JobOfferApplicationPermission(UserPermission userPermission) {
        this.userPermission = userPermission;
    }

    public boolean canCreateJobOfferApplication(JobOfferApplication application, User user){

        if(
            this.userPermission.isFreelancer(user) &&
            application.getUser().equals(user)
        ){
            return true;
        }

        return false;
    }
}
