package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository){
        repository = userRepository;
    }

    public Optional<User> find(long id){
        return this.repository.findById(id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

}
