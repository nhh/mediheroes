package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.domain.user.Address;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.dto.DocumentResponse;
import com.mediheroes.mediheroes.dto.user.*;
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
import java.util.stream.StreamSupport;

@RequestMapping("/api/v1/users")
@RestController
@Transactional
public class UserController {

    private final UserService userService;

    public UserController (
        UserService userServiceImpl
    ) {
        userService = userServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        var user = userService
            .find(id)
            .map(UserResponse::new)
            .orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getAuthtoken() {
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PutMapping("/me/address")
    public ResponseEntity<UserResponse> updateMyAddress(
        @Valid @RequestBody UserAddressRequest userAddressRequest
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var address = new Address();
        address.setZip(userAddressRequest.getZip());
        address.setCity(userAddressRequest.getCity());
        address.setState(userAddressRequest.getState());
        address.setStreet(userAddressRequest.getStreet());

        user.setAddress(address);

        userService.save(user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserResponse> updateMe(
        @PathVariable Long id,
        @Valid @RequestBody UserProfileRequest userProfileRequest
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var profile = user.getProfile();

        profile.setFirstname(userProfileRequest.getFirstname());
        profile.setLastname(userProfileRequest.getLastname());
        profile.setEmail(userProfileRequest.getEmail());

        userService.updateProfile(user, profile, user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> register(
        @Valid @RequestBody UserRequest userRequest
    ){

        var user = new User();
        user.setAddress(userRequest.getAddress());

        var profile = user.getProfile();
        profile.setPassword(userRequest.getPassword());
        profile.setEmail(userRequest.getEmail());
        profile.setFirstname(userRequest.getFirstname());
        profile.setLastname(userRequest.getLastname());

        user.setProfile(profile);
        user.setActive(userRequest.isActive());
        user.setVerified(userRequest.isVerified());
        user.setType(User.Type.FREELANCER);

        userService.save(user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    // Todo Rewrite into Service!
    @GetMapping
    public ResponseEntity<UserResponse[]> getAllUser() {
        var userList = StreamSupport
            .stream(userService.findAllUsers().spliterator(), false)
            .map(UserResponse::new)
            .toArray(UserResponse[]::new);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/{id}/profile/image")
    public ResponseEntity<String>uploadProfilePicture(
        @PathVariable Long id,
        @RequestBody MultipartFile file
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var fileId = userService.updateProfileImage(user, file, user);

        return new ResponseEntity<>(fileId, HttpStatus.OK);
    }

    @GetMapping("/{userId}/files/{id}")
    public ResponseEntity getUploadedFile(
        @PathVariable Long userId,
        @PathVariable String id
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var resource = userService
            .getUploadedFileResource(id)
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

    @GetMapping("/{id}/files")
    @Transactional
    public ResponseEntity<DocumentResponse[]> getDocuments(@PathVariable Long id){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var response = userService.getFiles(user, sender)
            .stream()
            .map(DocumentResponse::new)
            .toArray(DocumentResponse[]::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/files")
    public ResponseEntity uploadFile(
        @PathVariable Long id,
        @RequestBody MultipartFile file
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        userService.addFile(user, file, user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
