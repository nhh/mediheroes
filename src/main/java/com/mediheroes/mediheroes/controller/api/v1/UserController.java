package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Address;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
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

    public UserController (
        UserService userServiceImpl
    ) {
        userService = userServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return new ResponseEntity<>(new UserResponse(userService.find(id).orElseThrow(EntityNotFoundException::new)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        var userList = new ArrayList<User>();
        userService.findAllUsers().forEach(userList::add);
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

    @GetMapping("/{id}/address")
    public ResponseEntity<Address> getAddress(){
        return null;
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Address> updateOrCreateAddress(){
        return null;
    }

}
