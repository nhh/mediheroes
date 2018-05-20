package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.UserRequest;
import com.mediheroes.mediheroes.dto.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    public UserController (
        UserService userServiceImpl,
        CompanyService companyServiceImpl
    ) {
        userService = userServiceImpl;
        companyService = companyServiceImpl;
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

    @PostMapping("")
    public ResponseEntity<UserResponse> register(
        @RequestBody UserRequest userRequest
    ){

        var user = new User();
        user.setAddress(userRequest.getAddress());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setActive(userRequest.isActive());
        user.setVerified(userRequest.isVerified());

        userService.save(user);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserResponse[]> getAllUser() {
        var userList = StreamSupport
            .stream(userService.findAllUsers().spliterator(), false)
            .map(UserResponse::new)
            .toArray(UserResponse[]::new);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
