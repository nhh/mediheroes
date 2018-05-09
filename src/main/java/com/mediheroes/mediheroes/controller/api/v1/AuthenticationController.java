package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.AuthenticationToken;
import com.mediheroes.mediheroes.dto.UserRequest;
import com.mediheroes.mediheroes.dto.UserResponse;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipal;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userServiceImpl) {
        userService = userServiceImpl;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getAuthtoken() {
        var user = new UserResponse(userService.getCurrentUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity getAuthtoken(HttpSession session) {
        try {
            var sessionId = session.getId();
            return new ResponseEntity<>(new AuthenticationToken(sessionId), HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/token")
    public ResponseEntity logout(HttpSession session) {
        try {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            // Already logged out
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){

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

}
