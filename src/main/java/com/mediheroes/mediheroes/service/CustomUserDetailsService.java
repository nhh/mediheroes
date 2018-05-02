package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.CustomUserDetails;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userServiceImpl){
        this.userService = userServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return userService.findByEmail(email.toLowerCase())
            .map(CustomUserDetails::new)
            .orElseThrow(EntityNotFoundException::new);
    }

}
