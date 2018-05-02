package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.dto.AuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipal;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

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

}
