package com.mediheroes.mediheroes.permission;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.user.File;
import com.mediheroes.mediheroes.domain.user.User;
import org.springframework.stereotype.Component;

@Component("userPermission")
public class UserPermission {
    public boolean canAddCompany(User user, Company company){
        return user.hasCompany() || company.getOwner() != null;
    }

    public boolean isAdmin(User user){
        return user.getType().equals(User.Type.ADMIN);
    }

    public boolean isFreelancer(User user){
        return user.getType().equals(User.Type.FREELANCER);
    }

    public boolean fileBelongsTo(File file, User user) {
        return user.getFiles().contains(file) || user.getProfile().getImageId().equals(file.getFileId());
    }

    public boolean imageBelongsTo(String imageId, User user) {
        return user.getProfile().getImageId().equals(imageId);
    }

}
