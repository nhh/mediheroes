package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.AuthenticationToken;
import com.mediheroes.mediheroes.dto.UserRequest;
import com.mediheroes.mediheroes.dto.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
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

    @GetMapping("/token")
    public ResponseEntity getToken(HttpSession session) {
        try {
            var sessionId = session.getId();
            return new ResponseEntity<>(new AuthenticationToken(sessionId), HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/token")
    public ResponseEntity deleteToken(HttpSession session) {
        try {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            // Already logged out
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
