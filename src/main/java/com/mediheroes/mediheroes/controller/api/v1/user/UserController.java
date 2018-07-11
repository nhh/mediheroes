package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.domain.user.Profile;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.dto.user.UserRequest;
import com.mediheroes.mediheroes.dto.user.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // Todo Refactor into authentication controller
    @PostMapping
    public ResponseEntity<UserResponse> register(
        @Valid @RequestBody UserRequest userRequest
    ){

        var user = new User();
        user.setAddress(userRequest.getAddress());

        var profile = new Profile();
        profile.setPassword(userRequest.getPassword());
        profile.setEmail(userRequest.getEmail());
        profile.setFirstname(userRequest.getFirstname());
        profile.setLastname(userRequest.getLastname());

        // TODO Refactor into service
        // TODO Add filter for duplicate accounts etc.
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

}
