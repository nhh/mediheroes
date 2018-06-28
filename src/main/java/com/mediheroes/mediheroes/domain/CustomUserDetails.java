package com.mediheroes.mediheroes.domain;

import com.mediheroes.mediheroes.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UserSession userSession;

    public CustomUserDetails(User userImpl){
        userSession = new UserSession(userImpl);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userSession.getRoles().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.userSession.isActive();
    }

    @Override
    public String getUsername(){
        return this.userSession.getEmail().toLowerCase();
    }

    @Override
    public String getPassword(){
        return this.userSession.getPassword();
    }
}


