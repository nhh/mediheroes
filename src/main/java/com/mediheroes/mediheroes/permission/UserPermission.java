package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.stereotype.Component;

@Component("userPermission")
public class UserPermission {

    private final UserService userService;

    public UserPermission(UserService userService) {
        this.userService = userService;
    }

    public boolean canAddCompany(User user, Company company){

        if (user.hasCompany()){
            return false;
        }

        if (company.getOwner() != null){
            return false;
        }

        return true;
    }

}
