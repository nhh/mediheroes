package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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
        var user = userService.find(id).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<UserResponse>> getAllUser() {
        var userList = new ArrayList<UserResponse>();
        userService.findAllUsers().forEach((user -> userList.add(new UserResponse(user))));
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = "application/json")
    @Transactional
    public ResponseEntity<UserResponse> createUser(
        @Valid @RequestBody User newUser
    ){
        var user = userService.save(newUser);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }
}
