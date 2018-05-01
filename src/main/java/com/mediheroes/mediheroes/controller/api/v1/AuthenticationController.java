package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.dto.AuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    @GetMapping("/token")
    public ResponseEntity<AuthenticationToken>getAuthtoken(HttpSession session) {
        return new ResponseEntity<>(new AuthenticationToken(session.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/token")
    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
