package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.Location;
import org.springframework.stereotype.Component;

@Component("companyPermission")
public class CompanyPermission {

    public boolean canAddLocation(Company company, Location location){
        return true;
    }

}
