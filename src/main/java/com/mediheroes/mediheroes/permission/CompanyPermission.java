package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.domain.User;
import org.springframework.stereotype.Component;

@Component("companyPermission")
public class CompanyPermission {

    public boolean canAddLocation(Company company, Location location){
        return true;
    }

    public boolean canAddJobOffer(Company company, User user){
        return true;
    }

    public boolean canGetJobOffers(Company company, User user){
        return true;
    }

    public boolean canDeleteJobOffer(Company company, User user){
        return true;
    }
}
