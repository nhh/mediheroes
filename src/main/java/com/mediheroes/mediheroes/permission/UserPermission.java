package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.stereotype.Component;

@Component("userPermission")
public class UserPermission {

    public final UserService userService;

    public UserPermission(UserService userService) {
        this.userService = userService;
    }

    public boolean canAddCompany(User user, Company company){
        var currentUser = userService.getCurrentUser();

        if (user.getId() != currentUser.getId()){
            return false;
        }

        if (user.hasCompany()){
            return false;
        }

        if (company.getOwner() != null){
            return false;
        }

        return true;
    }

}
