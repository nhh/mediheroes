package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.domain.user.Address;
import com.mediheroes.mediheroes.dto.user.UserAddressRequest;
import com.mediheroes.mediheroes.dto.user.UserAddressResponse;
import com.mediheroes.mediheroes.dto.user.UserResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.UserService;
import org.apache.tomcat.util.codec.EncoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/users")
@RestController
@Transactional
public class AddressController {

    private final UserService userService;

    public AddressController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<UserResponse> updateAddress(
        @PathVariable Long id,
        @Valid @RequestBody UserAddressRequest userAddressRequest
    ){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var address = new Address();
        address.setZip(userAddressRequest.getZip());
        address.setCity(userAddressRequest.getCity());
        address.setState(userAddressRequest.getState());
        address.setStreet(userAddressRequest.getStreet());

        userService.updateAddress(user, address, sender);

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<UserAddressResponse> getAddress(
        @PathVariable Long id
    ){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var response = userService
            .getAddress(user, sender)
            .map(UserAddressResponse::new)
            .orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
