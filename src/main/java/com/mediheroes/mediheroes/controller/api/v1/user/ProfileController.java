package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.dto.user.UserProfileRequest;
import com.mediheroes.mediheroes.dto.user.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
@Transactional
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}/profile/image")
    public ResponseEntity<String> uploadProfilePicture(
        @PathVariable Long id,
        @RequestBody MultipartFile file
    ){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var imageId = userService.updateProfileImage(user, file, sender);

        return new ResponseEntity<>(imageId, HttpStatus.OK);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserResponse> updateMe(
        @PathVariable Long id,
        @Valid @RequestBody UserProfileRequest userProfileRequest
    ){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var profile = user.getProfile();

        profile.setFirstname(userProfileRequest.getFirstname());
        profile.setLastname(userProfileRequest.getLastname());
        profile.setEmail(userProfileRequest.getEmail());

        userService.updateProfile(user, profile, sender);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

}
