package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.dto.user.UserProfileRequest;
import com.mediheroes.mediheroes.dto.user.UserProfileResponse;
import com.mediheroes.mediheroes.dto.user.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.exception.FileNotFoundException;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getProfile(
        @PathVariable Long id
    ) {
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var response = userService
            .getProfile(user, sender)
            .map(UserProfileResponse::new)
            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/profile/image/{imageId}")
    public ResponseEntity getUploadedImage(
        @PathVariable Long userId,
        @PathVariable String imageId
    ){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(userId)
            .orElseThrow(EntityNotFoundException::new);

        var resource = userService
            .getUploadedImageResource(user, imageId, sender)
            .orElseThrow(FileNotFoundException::new);

        try {
            return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.asMediaType(MimeType.valueOf(resource.getContentType())))
                .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
