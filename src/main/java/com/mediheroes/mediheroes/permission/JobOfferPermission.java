package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.JobOffer;
import com.mediheroes.mediheroes.domain.User;
import org.springframework.stereotype.Component;

@Component("jobOfferPermission")
public class JobOfferPermission {

    public boolean canCreateJobOffer(JobOffer jobOffer, User user) {
        if(user.getType().equals(User.Type.FREELANCER)) {
            return false;
        }

        if(jobOffer.getCompany().equals(user.getCompany())) {
            return true;
        } else if(!jobOffer.getCompany().equals(user.getCompany())){
            return false;
        }

        if(jobOffer.getCompany().equals(user.getEmployer())) {
            return true;
        }

        return false;
    }

    public boolean canGetJobOffersForCompany(Company company, User user) {
        return company.getOwner().equals(user);
        //if (company.getEmployees().contains(user))
    }

    public boolean canDeleteJobOffer(JobOffer jobOffer, User user) {
        return user.getCompany().equals(jobOffer.getCompany());
    }

    public boolean canUpdateJobOffer(JobOffer jobOffer, User user) {
        return user.getCompany().equals(jobOffer.getCompany());
    }
}
