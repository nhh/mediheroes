package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.stereotype.Component;

@Component("userPermission")
public class UserPermission {

    public boolean canAddCompany(User user, Company company){
        return user.hasCompany() || company.getOwner() != null;
    }

}
