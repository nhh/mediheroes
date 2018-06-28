package com.mediheroes.mediheroes.repository;

import com.mediheroes.mediheroes.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByProfile_Email(String email);
}
